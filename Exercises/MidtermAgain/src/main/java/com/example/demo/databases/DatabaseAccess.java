package com.example.demo.databases;

import com.example.demo.beans.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Mission> getMissions() {
        String query = "SELECT * FROM MISSION";
        List<Mission> missions = jdbcTemplate.query(query, (rs, rowNum) -> {
            Mission mission = new Mission();
            mission.setId(rs.getLong("id"));
            mission.setAgent(rs.getString("agent"));
            mission.setTitle(rs.getString("title"));
            mission.setGadget1(rs.getString("gadget1"));
            mission.setGadget2(rs.getString("gadget2"));
            return mission;
        });
        return missions;
    }

    public void insertMission(Mission mission) {
        String query = "INSERT INTO MISSION (agent, title, gadget1, gadget2)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, mission.getAgent(), mission.getTitle(), mission.getGadget1(), mission.getGadget2());

    }

    public int agentCount() {
        String query = "SELECT COUNT(*) FROM MISSION";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<String> getDistinctAgentNames() {
        String query = "SELECT DISTINCT agent FROM MISSION";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public int deleteMission(long id) {
        String query = "DELETE FROM MISSION WHERE id = ?";
        int returnValue = jdbcTemplate.update(query, id);
        return returnValue;
    }

    public Mission getMissionById(long id) {
        String query = "SELECT * FROM MISSION WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Mission mission = new Mission();
            mission.setId(rs.getLong("id"));
            mission.setAgent(rs.getString("agent"));
            mission.setTitle(rs.getString("title"));
            mission.setGadget1(rs.getString("gadget1"));
            mission.setGadget2(rs.getString("gadget2"));
            return mission;
        });
    }

    public void updateMission(Mission mission) {
        String query = "UPDATE MISSION SET agent=?, title=?, gadget1=?, gadget2=? WHERE id=?";
        jdbcTemplate.update(query, mission.getAgent(), mission.getTitle(), mission.getGadget1(), mission.getGadget2(), mission.getId());
    }



}
