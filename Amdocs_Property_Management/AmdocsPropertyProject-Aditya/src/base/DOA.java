
package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DOA {

    

    // Inserting a new property record
    public void insert(Property obj) {
        try (Connection connection = DBA_Connector.getConnection()) {
            String insertQuery = "INSERT INTO propdetails (propertyId, noOfRooms, areaInSqft, floorNo, city, state, cost, ownerName, ownerContactNo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            statement.setInt(1, obj.getPropertyId());
            statement.setString(2, obj.getNoOfRooms());
            statement.setDouble(3, obj.getAreaInSqft());
            statement.setInt(4, obj.getFloorNo());
            statement.setString(5, obj.getCity());
            statement.setString(6, obj.getState());
            statement.setDouble(7, obj.getCost());
            statement.setString(8, obj.getOwnerName());
            statement.setString(9, obj.getOwnerContactNo());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



 // Updating property cost by propertyId
    public void updateCost(int propertyId, double newCost) {
        try (Connection connection = DBA_Connector.getConnection()) {
            String updateQuery = "UPDATE propdetails SET cost = ? WHERE propertyId = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);

            statement.setDouble(1, newCost);
            statement.setInt(2, propertyId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    // Retrieving details of all properties
    public List<Property> getAllDetails() {
        List<Property> detailsList = new ArrayList<>();

        try (Connection connection = DBA_Connector.getConnection()) {
            String displayQuery = "SELECT * FROM propdetails";
            PreparedStatement statement = connection.prepareStatement(displayQuery);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Property obj = new Property();

                obj.setPropertyId(rs.getInt("propertyId"));
                obj.setNoOfRooms(rs.getString("noOfRooms"));
                obj.setAreaInSqft(rs.getDouble("areaInSqft"));
                obj.setFloorNo(rs.getInt("floorNo"));
                obj.setCity(rs.getString("city"));
                obj.setState(rs.getString("state"));
                obj.setCost(rs.getDouble("cost"));
                obj.setOwnerName(rs.getString("ownerName"));
                obj.setOwnerContactNo(rs.getString("ownerContactNo"));

                detailsList.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailsList;
    }

    // Deleting a property by propertyId
    public void deleteProperty(int propertyId) {
        try (Connection connection = DBA_Connector.getConnection()) {
            String deleteQuery = "DELETE FROM propdetails WHERE propertyId = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, propertyId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Searching for properties by city
    public List<Property> searchByCity(String city) throws PropertyNotFoundException {
        List<Property> propertiesInCity = new ArrayList<>();

        try (Connection connection = DBA_Connector.getConnection()) {
            String searchQuery = "SELECT * FROM propdetails WHERE city = ?";
            PreparedStatement statement = connection.prepareStatement(searchQuery);
            statement.setString(1, city);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Property obj = new Property();

                obj.setPropertyId(rs.getInt("propertyId"));
                obj.setNoOfRooms(rs.getString("noOfRooms"));
                obj.setAreaInSqft(rs.getDouble("areaInSqft"));
                obj.setFloorNo(rs.getInt("floorNo"));
                obj.setCity(rs.getString("city"));
                obj.setState(rs.getString("state"));
                obj.setCost(rs.getDouble("cost"));
                obj.setOwnerName(rs.getString("ownerName"));
                obj.setOwnerContactNo(rs.getString("ownerContactNo"));

                propertiesInCity.add(obj);
            }

            if (propertiesInCity.isEmpty()) {
                throw new PropertyNotFoundException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propertiesInCity;
    }

    
 // Modify the method to return the list of properties
    public List<Property> searchByCost(int propmin, int propmax) throws PropertyNotFoundException {
        List<Property> propertiesInPriceRange = new ArrayList<>();

        try (Connection connection = DBA_Connector.getConnection()) {
            String searchByPriceRangeQuery = "SELECT * FROM propdetails WHERE cost >= ? AND cost <= ?";
            PreparedStatement statement = connection.prepareStatement(searchByPriceRangeQuery);
            statement.setInt(1, propmin);
            statement.setInt(2, propmax);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Property obj = new Property();

                obj.setPropertyId(rs.getInt("propertyId"));
                obj.setNoOfRooms(rs.getString("noOfRooms"));
                obj.setAreaInSqft(rs.getDouble("areaInSqft"));
                obj.setFloorNo(rs.getInt("floorNo"));
                obj.setCity(rs.getString("city"));
                obj.setState(rs.getString("state"));
                obj.setCost(rs.getDouble("cost"));
                obj.setOwnerName(rs.getString("ownerName"));
                obj.setOwnerContactNo(rs.getString("ownerContactNo"));

                propertiesInPriceRange.add(obj);
            }

            if (propertiesInPriceRange.isEmpty()) {
                throw new PropertyNotFoundException();
            }

            // Display the properties within the method
            for (Property property : propertiesInPriceRange) {
                System.out.println(property); // Assuming Property class has a meaningful toString method
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propertiesInPriceRange;
    }

    
   
    
    public List<Property> searchByNoOfRoomsAndCity(String room, String city) {
        List<Property> list1 = new ArrayList<Property>();

        try (Connection connection = DBA_Connector.getConnection()) {
            String searchQuery = "SELECT * FROM propdetails WHERE noOfRooms = ? AND city = ?";
            PreparedStatement pst = connection.prepareStatement(searchQuery);
            pst.setString(1, room);
            pst.setString(2, city);

            ResultSet rs = pst.executeQuery();

            System.out.println("After select statement");

            while (rs.next()) {
                Property obj = new Property();
                obj.setPropertyId(rs.getInt(1));
                obj.setNoOfRooms(rs.getString(2));
                obj.setAreaInSqft(rs.getInt(3));
                obj.setFloorNo(rs.getInt(4));
                obj.setCity(rs.getString(5));
                obj.setState(rs.getString(6));
                obj.setCost(rs.getInt(7));
                obj.setOwnerName(rs.getString(8));
                obj.setOwnerContactNo(rs.getString(9));
                list1.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list1;
    }
    
    
    

    	
}
