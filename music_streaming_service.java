import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class music_streaming_service {

   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/music_streaming_service?useSSL=false";
   static final String USER = "root";
   static final String PASS = "THEROCK123";
   static Scanner sc = new Scanner(System.in);
   static String user,country;

   static String hyphen(int n) {
      char[] charArray = new char[n];
      Arrays.fill(charArray, '-');
      return new String(charArray);
   }

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;

      try{
         // Initialization
         Class.forName(JDBC_DRIVER);
         System.out.println("Connecting to Music Streaming Service");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         stmt = conn.createStatement();
         sc.useDelimiter("\n");

         // Working
         System.out.println("\nWelcome to Music Streaming Service");
         main_menu(stmt);
         System.out.println("\nThank You for using Music Streaming Service");

         // Cleaning
         stmt.close();
         conn.close();
         sc.close();
      }
      catch(SQLException se){
            se.printStackTrace();
         }catch(Exception e){
         e.printStackTrace();
      }
      finally{
         try{
            if(stmt!=null)
               stmt.close();
         }
         catch(SQLException se2){
            se2.printStackTrace();
         }
         try{
            if(conn!=null)
               conn.close();
         }
         catch(SQLException se){
            se.printStackTrace();
         }
      }
   }

   static void main_menu(Statement stmt) {
      while(true) {
         System.out.println("\nSelect Login Method:");
         System.out.println("1. User");
         System.out.println("2. Artist");
         System.out.println("3. Music Distributor");
         System.out.println("4. Administrator");
         System.out.println("5. Exit");
         System.out.print("Enter Your Choice:");
         int choice = sc.nextInt();
         switch(choice) {
            case 1:
               user(stmt);
               break;
            case 2:
               authenticate_artist(stmt);
               break;
            case 3:
               authenticate_distributor(stmt);
               break;
            case 4:
               authenticate_admin(stmt);
               break;
            case 5:
               return;
            default:
               System.out.println("\nPlease Enter Valid Choice");
               break;
         }
      }
   }

   static void user(Statement stmt) {
      while(true) {
         System.out.println("\nUser Menu:");
         System.out.println("1. List All Available Songs");
         System.out.println("2. Search Songs by Genre");
         System.out.println("3. Search Songs by Artist");
         System.out.println("4. Search Songs by Album");
         System.out.println("5. Search Song by Song Name");
         System.out.println("6. Exit");
         System.out.print("Enter Your Choice:");
         int choice = sc.nextInt();
         switch(choice) {
            case 1:
               user_1(stmt);
               break;
            case 2:
               user_2(stmt);
               break;
            case 3:
               user_3(stmt);
               break;
            case 4:
               user_4(stmt);
               break;
            case 5:
               user_5(stmt);
               break;
            case 6:
               return;
            default:
               System.out.println("\nPlease Enter Valid Choice");
               break;
         }
      }
   }

   static void user_1(Statement stmt) {
      String sql = "select name 'Song', genre 'Genre', length 'Length' from song";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Song Available in database");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %-8s |\n", "Song","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void user_2(Statement stmt) {
      System.out.print("\nEnter Genre:");
      String sql = "select name 'Song', genre 'Genre', length 'Length' from song where genre = '" + sc.next() + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Song Available in given Genre");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %-8s |\n", "Song","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void user_3(Statement stmt) {
      System.out.print("\nEnter Artist:");
      String sql = "select s.name 'Song',s.genre 'Genre',s.length 'Length' from song s inner join album al on s.album_id = al.album_id inner join artist ar on al.artist_id = ar.artist_id where ar.name = '" + sc.next() + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Song Available in given Artist");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %-8s |\n", "Song","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void user_4(Statement stmt) {
      System.out.print("\nEnter Album:");
      String sql = "select s.name 'Song',s.genre 'Genre',s.length 'Length' from album a inner join song s on a.album_id = s.album_id where a.name = '" + sc.next() + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Song Available in given Album");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %-8s |\n", "Song","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void user_5(Statement stmt) {
      System.out.print("\nEnter Song Name:");
      String sql = "select name 'Song',genre 'Genre',length 'Length' from song where name = '" + sc.next() + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Song Found");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %-8s |\n", "Song","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-15s | %s |\n", rs.getString("Song"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void authenticate_artist(Statement stmt) {
      System.out.print("\nEnter Artist Name you Belong to:");
      String name = sc.next();
      String sql = "select name from artist where name ='" + name + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.print("\nArtist not found, Would you like to try again? [y/n]:");
            Character c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y')
               authenticate_artist(stmt);
         }
         else {
            user = name;
            System.out.println("\nLogging into Artist:" + rs.getString("name"));
            artist(stmt);
            rs.close();
         }
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist(Statement stmt) {
      while(true) {
         System.out.println("\nArtist Menu:");
         System.out.println("1. Update Name");
         System.out.println("2. Update Origin Country");
         System.out.println("3. Update Email");
         System.out.println("4. Upload your Album");
         System.out.println("5. Delete Your Album");
         System.out.println("6. View Your Details");
         System.out.println("7. View Your Albums");
         System.out.println("8. Exit");
         System.out.print("Enter Your Choice:");
         int choice = sc.nextInt();
         switch(choice) {
            case 1:
               artist_1(stmt);
               break;
            case 2:
               artist_2(stmt);
               break;
            case 3:
               artist_3(stmt);
               break;
            case 4:
               artist_4(stmt);
               break;
            case 5:
               artist_5(stmt);
               break;
            case 6:
               artist_6(stmt);
               break;
            case 7:
               artist_7(stmt);
               break;
            case 8:
               return;
            default:
               System.out.println("\nPlease Enter Valid Choice");
               break;
         }
      }
   }

   static void artist_1(Statement stmt) {
      System.out.print("\nEnter your new Name:");
      String temp = sc.next();
      String sql = "update artist set name = '" + temp + "' where name = '" + user + "'";
      try {
         int rs = stmt.executeUpdate(sql);
         if(rs == 0) {
            System.out.println("\nError in Updating occured, please try again later");
         }
         else {
            user = temp;
            System.out.println("\nUpdate Successful");
         }
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_2(Statement stmt) {
      System.out.print("\nEnter your new Origin Country:");
      String sql = "update artist set origin_country = '" + sc.next() + "' where name = '" + user + "'";
      try {
         int rs = stmt.executeUpdate(sql);
         if(rs == 0)
            System.out.println("\nError in Updating occured, please try again later");
         else
            System.out.println("\nUpdate Successful");
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_3(Statement stmt) {
      System.out.print("\nEnter your new Email:");
      String sql = "update artist set email = '" + sc.next() + "' where name = '" + user + "'";
      try {
         int rs = stmt.executeUpdate(sql);
         if(rs == 0)
            System.out.println("\nError in Updating occured, please try again later");
         else
            System.out.println("\nUpdate Successful");
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_4(Statement stmt) {
      System.out.print("\nEnter Album Name:");
      String album_name = sc.next();
      System.out.print("\nEnter Release Date[YYYY-MM-DD]:");
      String date = sc.next();
      String sql;
      int ret,num;
      try {
         sql = "insert into album(name,release_date,artist_id,distributor_id) values('" + album_name + "','" + date + "',(select artist_id from artist where name = '" + user + "'),NULL)";
         ret = stmt.executeUpdate(sql);
         if(ret == 0) {
            System.out.println("\nError in Inserting new album, please try again later");
            return;
         }
         System.out.print("\nEnter number of songs in the new album:");
         num = sc.nextInt();
         String name,genre,time;
         for(int i = 0;i < num;i++) {
            System.out.print("\nSong " + (i + 1) + ":");
            System.out.print("\nEnter Song Name:");
            name = sc.next();
            System.out.print("\nEnter Song Genre:");
            genre = sc.next();
            System.out.print("\nEnter Song Length[HH:MM:SS]:");
            time = sc.next();
            sql = "insert into song(name,genre,length,album_id) values('" + name + "','" + genre + "','" + time + "',(select album_id from album where name = '" + album_name + "'))";
            ret = stmt.executeUpdate(sql);
            if(ret == 0) {
               System.out.println("\nError in Inserting new song, please try again later");
               return;
            }
         }
         System.out.println("\nAlbum Upload Successful");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_5(Statement stmt) {
      System.out.print("\nEnter Your Album Name:");
      String name = sc.next();
      String sql;
      int ret;
      try {
         sql = "select album_id from album where name = '" + name + "' and distributor_id is NULL";
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nGiven Album doesn't exist or Album edit can be done by your associated Music Distributor only");
            return;
         }
         int album_id = rs.getInt("album_id");
         sql = "select name from song where album_id = " + album_id;
         rs = stmt.executeQuery(sql);
         if(rs.next()) {
            sql = "delete from song where album_id = " + album_id;
            ret = stmt.executeUpdate(sql);
            if(ret == 0)
            System.out.println("\nError in Deleting Songs in Album, please try again later");
         else
            System.out.println("\nSongs in Album Deleted Successfully");
         }
         sql = "delete from album where name = '" + name + "' and distributor_id is NULL";
         ret = stmt.executeUpdate(sql);
         if(ret == 0)
            System.out.println("\nError in Deleting Album, please try again later");
         else
            System.out.println("\nAlbum Deleted Successfully");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_6(Statement stmt) {
      String sql = "select name, origin_country, email from artist where name = '" + user + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nArtist details not found");
         }
         else {
            System.out.println("\n+" + hyphen(32) + "+" + hyphen(22) + "+" + hyphen(32) + "+");
            System.out.format("| %-30s | %-20s | %-30s |\n", "Artist Name","Origin_Country","Email");
            System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+" + hyphen(32) + "+");
            System.out.format("| %-30s | %-20s | %-30s |\n", rs.getString("name"),rs.getString("origin_country"),rs.getString("email"));
            System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+" + hyphen(32) + "+");
         }
         rs.close();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void artist_7(Statement stmt) {
      String sql = "select a.name 'Album Name',s.name 'Song Name',s.genre 'Genre',s.length 'Length' from song s right join album a on a.album_id = s.album_id inner join artist at on at.artist_id = a.artist_id where at.name = '" + user + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Songs Uploaded by You");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", "Album Name","Song Name","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", rs.getString("Album Name"),rs.getString("Song Name"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", rs.getString("Album Name"),rs.getString("Song Name"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void authenticate_distributor(Statement stmt) {
      System.out.print("\nEnter Your Music Distributor Name:");
      String name = sc.next();
      System.out.print("\nEnter You Headquarters Country:");
      String temp = sc.next();
      String sql = "select name from music_distributor where name ='" + name + "' and headquarters_country = '" + temp + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.print("\nMusic Distributor not found, Would you like to try again? [y/n]:");
            Character c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y')
               authenticate_distributor(stmt);
         }
         else {
            user = name;
            country = temp;
            System.out.println("\nLogging into Music Distributor:" + name + ", " + temp);
            distributor(stmt);
         }
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void distributor(Statement stmt) {
      while(true) {
         System.out.println("\nMusic Distributor Menu:");
         System.out.println("1. Update Name");
         System.out.println("2. Upload Your Album");
         System.out.println("3. Delete Your Album");
         System.out.println("4. View Your Albums");
         System.out.println("5. View Your Details");
         System.out.println("6. Exit");
         System.out.print("Enter Your Choice:");
         int choice = sc.nextInt();
         switch(choice) {
            case 1:
               distributor_1(stmt);
               break;
            case 2:
               distributor_2(stmt);
               break;
            case 3:
               distributor_3(stmt);
               break;
            case 4:
               distributor_4(stmt);
               break;
            case 5:
               distributor_5(stmt);
               break;
            case 6:
               return;
            default:
               System.out.println("\nPlease Enter Valid Choice");
               break;
         }
      }
   }

   static void distributor_1(Statement stmt) {
      System.out.print("\nEnter your new Name:");
      String temp = sc.next();
      String sql = "update music_distributor set name = '" + temp + "' where name = '" + user + "' and headquarters_country = '" + country + "'";
      try {
         int rs = stmt.executeUpdate(sql);
         if(rs == 0) {
            System.out.println("\nError in Updating occured, please try again later");
         }
         else {
            user = temp;
            System.out.println("\nUpdate Successful");
         }
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void distributor_2(Statement stmt) {
      System.out.print("\nEnter Artist Name:");
      String artist_name = sc.next();
      System.out.print("\nEnter Album Name:");
      String album_name = sc.next();
      System.out.print("\nEnter Release Date[YYYY-MM-DD]:");
      String date = sc.next();
      String sql;
      int ret,num;
      try {
         sql = "insert into album(name,release_date,artist_id,distributor_id) values('" + album_name + "','" + date + "',(select artist_id from artist where name = '" + artist_name + "'),(select distributor_id from music_distributor where name = '" + user + "' and headquarters_country = '" + country + "'))";
         ret = stmt.executeUpdate(sql);
         if(ret == 0) {
            System.out.println("\nError in Inserting new album, please try again later");
            return;
         }
         System.out.print("\nEnter number of songs in the new album:");
         num = sc.nextInt();
         String name,genre,time;
         for(int i = 0;i < num;i++) {
            System.out.print("\nSong " + (i + 1) + ":");
            System.out.print("\nEnter Song Name:");
            name = sc.next();
            System.out.print("\nEnter Song Genre:");
            genre = sc.next();
            System.out.print("\nEnter Song Length[HH:MM:SS]:");
            time = sc.next();
            sql = "insert into song(name,genre,length,album_id) values('" + name + "','" + genre + "','" + time + "',(select album_id from album where name = '" + album_name + "'))";
            ret = stmt.executeUpdate(sql);
            if(ret == 0) {
               System.out.println("\nError in Inserting new song, please try again later");
               return;
            }
         }
         System.out.println("\nAlbum Upload Successful");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void distributor_3(Statement stmt) {
      System.out.print("\nEnter Your Album Name:");
      String name = sc.next();
      String sql;
      int ret;
      try {
         sql = "select album_id from album where name = '" + name + "' and distributor_id  = (select distributor_id from music_distributor where name = '" + user + "' and headquarters_country = '" + country + "')";
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nGiven Album doesn't exist or Album edit can be done by the Album's Artist only");
            return;
         }
         int album_id = rs.getInt("album_id");
         sql = "select name from song where album_id = " + album_id;
         rs = stmt.executeQuery(sql);
         if(rs.next()) {
            sql = "delete from song where album_id = " + album_id;
            ret = stmt.executeUpdate(sql);
            if(ret == 0)
               System.out.println("\nError in Deleting Songs in Album, please try again later");
            else
               System.out.println("\nSongs in Album Deleted Successfully");
         }
         sql = "delete from album where album_id = " + album_id;
         ret = stmt.executeUpdate(sql);
            if(ret == 0)
               System.out.println("\nError in Deleting Album, please try again later");
            else
               System.out.println("\nAlbum Deleted Successfully");
         rs.close();
      }
      catch(SQLException e) {
         e.printStackTrace();
      }
   }

   static void distributor_4(Statement stmt) {
      String sql = "select a.name 'Album Name',s.name 'Song Name', s.genre 'Genre', s.length 'Length' from song s right join album a on s.album_id = a.album_id inner join music_distributor d on d.distributor_id = a.distributor_id where d.name = '" + user + "' and d.headquarters_country = '" + country + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Albums Distributed by You");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", "Album Name","Song Name","Genre","Length");
         System.out.println("+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", rs.getString("Album Name"),rs.getString("Song Name"),rs.getString("Genre"),rs.getString("Length"));
         while(rs.next()) {
            System.out.format("| %-30s | %-30s | %-15s | %-8s |\n", rs.getString("Album Name"),rs.getString("Song Name"),rs.getString("Genre"),rs.getString("Length"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(32) + "+" + hyphen(17) + "+" + hyphen(10) + "+");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void distributor_5(Statement stmt) {
      String sql = "select name 'Name',headquarters_country 'Headquarters Country' from music_distributor where name = '" + user + "'and headquarters_country = '" + country + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nMusic Distributor details not found");
         }
         else {
            System.out.println("\n+" + hyphen(32) + "+" + hyphen(22) + "+");
            System.out.format("| %-30s | %-20s |\n", "Music Distributor Name","Headquarters Country");
            System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+");
            System.out.format("| %-30s | %-20s |\n", rs.getString("Name"),rs.getString("Headquarters Country"));
            System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+");
         }
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void authenticate_admin(Statement stmt) {
      System.out.print("\nEnter Admin Username:");
      String name = sc.next();
      System.out.print("\nEnter Password:");
      String password = sc.next();
      String sql = "select username from admin where username = '" + name + "' and password = '" + password + "'";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.print("\nAdmin not found, Would you like to try again? [y/n]:");
            Character c = sc.next().charAt(0);
            if(c == 'y' || c == 'Y')
               authenticate_admin(stmt);
         }
         else {
            user = name;
            System.out.println("\nLogging into Music Distributor:" + rs.getString("username"));
            admin(stmt);
         }
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin(Statement stmt) {
      while(true) {
         System.out.println("\nAdmin Menu:");
         System.out.println("1. Insert Artist");
         System.out.println("2. Insert Distributor");
         System.out.println("3. Delete Artist");
         System.out.println("4. Delete Distributor");
         System.out.println("5. View All Artists");
         System.out.println("6. View All Distributors");
         System.out.println("7. Exit");
         System.out.print("Enter Your Choice:");
         int choice = sc.nextInt();
         switch(choice) {
            case 1:
               admin_1(stmt);
               break;
            case 2:
               admin_2(stmt);
               break;
            case 3:
               admin_3(stmt);
               break;
            case 4:
               admin_4(stmt);
               break;
            case 5:
               admin_5(stmt);
               break;
            case 6:
               admin_6(stmt);
               break;
            case 7:
               return;
            default:
               System.out.println("\nPlease Enter Valid Choice");
               break;
         }
      }
   }

   static void admin_1(Statement stmt) {
      System.out.print("\nEnter Artist Name:");
      String name = sc.next();
      System.out.print("\nEnter Origin Country:");
      String origin = sc.next();
      System.out.print("\nEnter Email:");
      String email = sc.next();
      String sql = "insert into artist(name,origin_country,email) values('" + name + "','" + origin + "','"+ email + "')";
      try {
         int ret = stmt.executeUpdate(sql);
         if(ret == 0) {
            System.out.println("Error in Inserting new Artist");
            return;
         }
         System.out.println("\nInsertion of new Artist Successful");
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin_2(Statement stmt) {
      System.out.print("\nEnter Music Distributor Name:");
      String name = sc.next();
      System.out.print("\nEnter Headquarters Country:");
      String headquarters = sc.next();
      String sql = "insert into music_distributor(name,headquarters_country) values('" + name + "','" + headquarters + "')";
      try {
         int ret = stmt.executeUpdate(sql);
         if(ret == 0) {
            System.out.println("Error in Inserting new Music Distributor");
            return;
         }
         System.out.println("\nInsertion of new Music Distributor Successful");
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin_3(Statement stmt) {
      System.out.print("\nEnter Artist Name:");
      String name = sc.next();
      String sql = "select artist_id from artist where name = '" + name + "'";
      int ret;
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nGiven Artist doesn't exist");
            return;
         }
         int artist_id = rs.getInt("artist_id");
         sql = "select album_id from album where artist_id = " + artist_id;
         rs = stmt.executeQuery(sql);
         if(rs.next()) {
            sql = "select name from song where album_id in (select album_id from album where artist_id = " + artist_id + ")";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
               sql = "delete from song where album_id in (select album_id from album where artist_id = " + artist_id + ")";
               ret = stmt.executeUpdate(sql);
               if(ret == 0)
                  System.out.println("\nError in Deleting Songs in Artist's Albums, please try again later");
               else
                  System.out.println("\nSongs in Artist's Albums Deleted Successfully");
            }
            sql = "delete from album where artist_id = " + artist_id;
            ret = stmt.executeUpdate(sql);
            if(ret == 0)
               System.out.println("\nError in Deleting Artist's Albums, please try again later");
            else
               System.out.println("\nArtist's Albums Deleted Successfully");
         }
         sql = "delete from artist where name = '" + name + "'";
         ret = stmt.executeUpdate(sql);
         if(ret == 0)
            System.out.println("\nError in Deleting Artist, please try again later");
         else
            System.out.println("\nArtist Successfully Deleted");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin_4(Statement stmt) {
      System.out.print("\nEnter Distributor Name:");
      String name = sc.next();
      System.out.print("\nEnter Distributor Headquarters Country:");
      String headquarters = sc.next();
      String sql = "select distributor_id from music_distributor where name = '" + name + "' and headquarters_country = '" + headquarters + "'";
      int ret;
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nGiven Distributor doesn't exist");
            return;
         }
         int distributor_id = rs.getInt("distributor_id");
         sql = "select album_id from album where distributor_id = " + distributor_id;
         rs = stmt.executeQuery(sql);
         if(rs.next()) {
            sql = "select name from song where album_id in (select album_id from album where distributor_id = " + distributor_id + ")";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
               sql = "delete from song where album_id in (select album_id from album where distributor_id = " + distributor_id + ")";
               ret = stmt.executeUpdate(sql);
               if(ret == 0)
                  System.out.println("\nError in Deleting Songs in Distributor's Albums, please try again later");
               else
                  System.out.println("\nSongs in Distributor's Albums Deleted Successfully");
            }
            sql = "delete from album where distributor_id = " + distributor_id;
            ret = stmt.executeUpdate(sql);
            if(ret == 0)
               System.out.println("\nError in Deleting Distributor's Albums, please try again later");
            else
               System.out.println("\nDistributor's Albums Successfully Deleted");
         }
         sql = "delete from music_distributor where distributor_id = " + distributor_id;
         ret = stmt.executeUpdate(sql);
         if(ret == 0)
            System.out.println("\nError in Deleting Music Distributor, please try again later");
         else
            System.out.println("\nMusic Distributor Successfully Deleted");
         rs.close();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin_5(Statement stmt) {
      String sql = "select name from artist";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Artists in Database");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+");
         System.out.format("| %-30s |\n", "Artist Name");
         System.out.println("+" + hyphen(32) + "+");
         System.out.format("| %-30s |\n", rs.getString("name"));
         while(rs.next()) {
            System.out.format("| %-30s |\n", rs.getString("name"));;
         }
         System.out.println("+" + hyphen(32) + "+");
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void admin_6(Statement stmt) {
      String sql = "select name,headquarters_country from music_distributor";
      try {
         ResultSet rs = stmt.executeQuery(sql);
         if(!rs.next()) {
            System.out.println("\nNo Artists in Database");
            return;
         }
         System.out.println("\n+" + hyphen(32) + "+" + hyphen(22) + "+");
            System.out.format("| %-30s | %-20s |\n", "Music Distributor Name","Headquarters Country");
            System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+");
            System.out.format("| %-30s | %-20s |\n", rs.getString("name"),rs.getString("headquarters_country"));
         while(rs.next()) {
            System.out.format("| %-30s | %-20s |\n", rs.getString("name"),rs.getString("headquarters_country"));
         }
         System.out.println("+" + hyphen(32) + "+" + hyphen(22) + "+");
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}