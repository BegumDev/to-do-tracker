package com.example.project;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectRepository {

    @Inject
    DataSource dataSource;

    public List<Project> findAll() {
        String sql = """
                SELECT id, name, description, created_at
                FROM projects
                ORDER BY id
                """;

        List<Project> projects = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Project project = new Project();
                project.id = result.getLong("id");
                project.name = result.getString("name");
                project.description = result.getString("description");
                project.createdAt = result.getTimestamp("created_at")
                        .toLocalDateTime();

                projects.add(project);
            }

            return projects;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Could not retrieve projects", e);
        }
    }
}