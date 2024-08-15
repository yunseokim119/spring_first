package com.sparta.springfirstys.repository;

import com.sparta.springfirstys.entity.Event;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    private final String url = "jdbc:mysql://localhost:3306/springboot";
    private final String username = "root";
    private final String password = "poi1109~";

    public void save(Event event) {
        String sql = "INSERT INTO Event (task, manager, start_time, end_time, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1, event.getTask());
            pstmt.setString(2, event.getManager());
            pstmt.setTimestamp(3, Timestamp.valueOf(event.getStartTime()));
            pstmt.setTimestamp(4, Timestamp.valueOf(event.getEndTime()));
            pstmt.setString(5, event.getPassword());
            pstmt.setTimestamp(6, Timestamp.valueOf(event.getCreatedAt()));
            pstmt.setTimestamp(7, Timestamp.valueOf(event.getUpdatedAt()));

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Event findById(Long id) {
        String sql = "SELECT * FROM Event WHERE id = ?";
        Event event = null;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    event = mapRowToEvent(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }
    public List<Event> findAll() {
        String sql = "SELECT * FROM Event";
        List<Event> events = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                events.add(mapRowToEvent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void update(Event event) {
        String sql = "UPDATE Event SET task = ?, manager = ?, updated_at = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, event.getTask());
            pstmt.setString(2, event.getManager());
            pstmt.setTimestamp(3, Timestamp.valueOf(event.getUpdatedAt()));
            pstmt.setLong(4, event.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Long id) {
        String sql = "DELETE FROM Event WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Event mapRowToEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setTask(rs.getString("taske"));
        event.setManager(rs.getString("manager"));
        event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        event.setPassword(rs.getString("password"));
        event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        event.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return event;
    }
}
