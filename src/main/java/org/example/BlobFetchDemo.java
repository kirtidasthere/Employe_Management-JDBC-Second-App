package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;

public class BlobFetchDemo
{
    //jdbc basic credential
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/spark";
    private static final String USER_NAME="root";
    private static final String PASSWORD="W7301@jqirk";

    private static Connection connection;
    private static PreparedStatement preparedStatement; // interface bhi hai or statement bhi
    private static ResultSet resultSet;

    public static void main(String[] args)  {
        try {

            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
            preparedStatement =connection.prepareStatement("Select image from blobdemo where id = ?");
            preparedStatement.setInt(1,2);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                //stream hold
                InputStream is= resultSet.getBinaryStream("image");
                        //location
                FileOutputStream fis= new FileOutputStream("D:\\programs\\Code For Success\\projects\\JDBC-second-App\\src\\main\\java\\res\\kirtidas.jpg");

                byte[] buffer= new byte[1024];
                while (is.read(buffer)>0)
                {
                    fis.write(buffer);
                }
                fis.close();
                is.close();
                System.out.println("Data Fetched...");
            }
            else
            {
                System.out.println("No Image found...");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
