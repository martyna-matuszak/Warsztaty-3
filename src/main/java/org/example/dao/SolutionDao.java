package org.example.dao;


import org.example.model.Exercise;
import org.example.model.Solution;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solutions(created, updated, description, exercise_id, user_id, grade) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solutions SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ?, grade = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solutions";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY =
            "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY =
            "SELECT * FROM solutions WHERE exercise_id = ? ORDER BY created DESC";
    private static final String FIND_RECENT_SOLUTIONS_QUERY =
            "SELECT * FROM solutions ORDER BY created desc LIMIT ?";
    private static final String GET_GPA_QUERY =
            "SELECT AVG(grade) FROM solutions WHERE user_id = ?";

    public Solution create(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            prepareSolutionStatement(statement, solution);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void prepareSolutionStatement(PreparedStatement statement, Solution solution) throws SQLException {
        statement.setString(1, solution.getCreated());
        statement.setString(2, solution.getUpdated());
        statement.setString(3, solution.getDescription());
        statement.setInt(4, solution.getExerciseId());
        statement.setInt(5, solution.getUserId());
        statement.setDouble(6,solution.getGrade());
    }

    public Solution read(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getSolutionResult(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Solution getSolutionResult(ResultSet resultSet) throws SQLException {
        Solution solution = new Solution();
        solution.setId(resultSet.getInt("id"));
        solution.setCreated(resultSet.getString("created"));
        solution.setUpdated(resultSet.getString("updated"));
        solution.setDescription(resultSet.getString("description"));
        solution.setExerciseId(resultSet.getInt("exercise_id"));
        solution.setUserId(resultSet.getInt("user_id"));
        solution.setGrade(resultSet.getDouble("grade"));
        return solution;
    }

    public void update(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            prepareSolutionStatement(statement,solution);
            statement.setInt(7, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmp = Arrays.copyOf(solutions, solutions.length + 1);
        tmp[solutions.length] = s;
        return tmp;
    }

    private Solution[] createSolutionsArray(ResultSet resultSet) throws SQLException {
        Solution[] solutions = new Solution[0];
        while (resultSet.next()) {
            Solution solution = getSolutionResult(resultSet);
            solutions = addToArray(solution, solutions);
        }
        return solutions;
    }

    public Solution[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            return createSolutionsArray(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] findSolutions (String query, int parameter){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            return createSolutionsArray(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllByUserId(int userId) {
        return findSolutions(FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY, userId);
    }

    public Solution[] findAllByExerciseId (int exerciseId){
        return findSolutions(FIND_ALL_SOLUTIONS_BY_EXERCISE_ID_QUERY, exerciseId);
    }

    public Solution[] findRecent (int limit){
        return findSolutions(FIND_RECENT_SOLUTIONS_QUERY, limit);
    }

    public String[][] getSolutionsWithExerciseDetails (Solution[] solutions){
        String[][] solutionsWithExercises = new String[solutions.length][6];
        ExerciseDao exerciseDao = new ExerciseDao();
        for(int i=0; i<solutions.length; i++){
            Solution solution = solutions[i];
            Exercise exercise = exerciseDao.read(solution.getExerciseId());
            String grade;
            if(solution.getGrade() == 0.0){
                grade = "none";
            } else {
                grade = String.valueOf(solution.getGrade());
            }
            String[] singleLine = {solution.getCreated(), solution.getUpdated(), solution.getDescription(), exercise.getTitle(), exercise.getDescription(), grade};
            solutionsWithExercises[i] = singleLine;
        }
        return solutionsWithExercises;
    }

    public Double getGpa(int userId){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_GPA_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("AVG(grade)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
