/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Post;
import Model.Post_Category;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class PostDAO extends DBContext {

    public List<Post> getAllPosts() {
        try {
            String sql = "select *from [Post]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            return getListPosts(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Post> getBlogforHomepage() {
        String sql = "select TOP 3 * from \n"
                + "Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.userID = u.Id\n"
                + "where p.postStatus = 1\n"
                + "order by p.updateDate";
        List<Post> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {
                Post posts = new Post();
                posts.setPostID(rs.getInt("postID"));
                posts.setThumbnail(rs.getString("thumbnail"));
                posts.setPostTitle(rs.getString("posttitle"));
                posts.setBriefInformation(rs.getString("briefInformation"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setEmail(rs.getString("email"));
                posts.setUserID(users);
                ls.add(posts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * get the 3 latest blogs having active status
     *
     * @param status
     * @return
     */
    public List<Post> getActiveRecentPostList(boolean status) {
        String sql = "select top 3 *\n"
                + "from Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.UserID = u.Id\n"
                + "where PostStatus = ?\n"
                + "order by PostDate DESC";
        List<Post> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {
                Post posts = new Post();
                posts.setPostID(rs.getInt("postID"));
                posts.setThumbnail(rs.getString("thumbnail"));
                posts.setPostTitle(rs.getString("posttitle"));
                posts.setBriefInformation(rs.getString("briefInformation"));
                posts.setPostDate(rs.getString("postdate"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setEmail(rs.getString("email"));
                posts.setUserID(users);
                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));

                ls.add(posts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * get the list of blog category having acitve status
     *
     * @param status
     * @return
     */
    public List<Post_Category> getActivePostCategory(boolean status) {
        String sql = "SELECT * from Post_Category where Status = ?";
        List<Post_Category> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stm.setBoolean(1, status);
            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {

                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));
                ls.add(postCate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Post_Category> getPostCategoryList() {
        String sql = "SELECT * from Post_Category";
        List<Post_Category> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {

                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));
                ls.add(postCate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * get the total count of blogs to count the end page index for pagination
     *
     * @return
     */
    public int getCountBlog() {
        String sql = "select count(*) from Post";
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            // use resultset to excute query and get the count number of all Post records
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * get the blog list which gets records per page
     *
     * @param status
     * @param index
     * @param records
     * @return
     */
    public List<Post> getPagingBlogList(boolean status, int index, int records) {
        String sql = "select *\n"
                + "from Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.UserID = u.Id\n"
                + "where PostStatus = ?\n"
                + "order by PostDate desc\n"
                + "offset ? rows fetch next ? rows only";
        List<Post> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            stm.setInt(2, (index - 1) * records);
            stm.setInt(3, records);
            ResultSet rs = stm.executeQuery();
            // loop all ResultSet and set data for each post
            while (rs.next()) {
                Post posts = new Post();
                posts.setPostID(rs.getInt("postID"));
                posts.setThumbnail(rs.getString("thumbnail"));
                posts.setPostTitle(rs.getString("posttitle"));
                posts.setBriefInformation(rs.getString("briefInformation"));
                posts.setPostDate(rs.getString("postdate"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setEmail(rs.getString("email"));
                posts.setUserID(users);
                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));

                ls.add(posts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * get the blog list which gets records per page by id of category
     *
     * @param status
     * @param id
     * @param index
     * @return
     */
    public List<Post> getPagingBlogByCategory(boolean status, int id, int index) {
        String sql = "select * from \n"
                + "Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.UserID = u.Id\n"
                + "where p.PostStatus = ? and pc.Status = ? and pc.PostCategoryID =?\n"
                + "order by PostDate desc\n"
                + "offset ? rows fetch next 2 rows only";
        List<Post> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            stm.setBoolean(2, status);
            stm.setInt(3, id);
            stm.setInt(4, (index - 1) * 2);
            ResultSet rs = stm.executeQuery();
            // loop all ResultSet and set data for each post
            while (rs.next()) {
                Post posts = new Post();
                posts.setPostID(rs.getInt("postID"));
                posts.setThumbnail(rs.getString("thumbnail"));
                posts.setPostTitle(rs.getString("posttitle"));
                posts.setBriefInformation(rs.getString("briefInformation"));
                posts.setPostDate(rs.getString("postdate"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setEmail(rs.getString("email"));
                posts.setUserID(users);
                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));

                ls.add(posts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * to get the count numbers of blog list which has the same categoryid
     *
     * @param id
     * @return
     */
    public int getCountBlogByCategory(int id) {
        String sql = "select count(*) from Post  where PostCategoryID = ?";
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            // loop all Result set to excute query and get the count of records of post
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * to get Blog list which has records per page by searching text
     *
     * @param status
     * @param text
     * @param index
     * @return
     */
    public List<Post> getPagingBlogListByText(boolean status, String text, int index) {

        String sql = "select * from Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.UserID = u.Id\n"
                + "where PostStatus = ? and pc.PostCategoryName like ? or p.PostTitle like ? or u.Email like ? \n"
                + "order by p.PostDate desc\n"
                + "offset ? rows fetch next 2 rows only";
        List<Post> ls = new ArrayList<>();
        //check preparestamenet not failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setBoolean(1, status);
            stm.setString(2, "%" + text + "%");
            stm.setString(3, "%" + text + "%");
            stm.setString(4, "%" + text + "%");
            stm.setInt(5, (index - 1) * 2);

            ResultSet rs = stm.executeQuery();
            // loop all ResultSet and set data for each post 
            while (rs.next()) {
                Post posts = new Post();
                posts.setPostID(rs.getInt("postID"));
                posts.setThumbnail(rs.getString("thumbnail"));
                posts.setPostTitle(rs.getString("posttitle"));
                posts.setBriefInformation(rs.getString("briefInformation"));
                posts.setPostDate(rs.getString("postdate"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setEmail(rs.getString("email"));
                posts.setUserID(users);
                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));

                ls.add(posts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    /**
     * to get the count number of the blog list searched by list
     *
     * @param status
     * @param text
     * @return
     */
    public int getCountSearchedBlog(boolean status, String text) {
        String sql = "select count(*) from Post p inner join Post_Category pc on p.PostCategoryID = pc.PostCategoryID\n"
                + "inner join [User] u on p.UserID = u.Id\n"
                + "where PostStatus = ? and pc.PostCategoryName like ? or p.PostTitle like ? or u.Email like ? \n";
        //check preparedStatement not be failed
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, "%" + text + "%");
            stm.setString(3, "%" + text + "%");
            stm.setString(4, "%" + text + "%");
            // loop all ResultSet and set data for each post 
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * get a post record by postID
     *
     * @param PostId
     * @return
     */
    public Post getPostbyID(int PostId) {
        Post posts = new Post();

        //check preparedStatement not be failed
        try {
            String sql = "select * \n"
                    + "from Post p inner join Post_Category pc\n"
                    + "on p.PostCategoryID = pc.PostCategoryID\n"
                    + "WHERE PostId = ?";

            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stm.setInt(1, PostId);
            ResultSet rs = stm.executeQuery();
            //use Resultset to excute query and set data for Post
            if (rs.next()) {

                Post a = new Post();
                a.setPostID(rs.getInt("PostId"));

                a.setThumbnail(rs.getString("Thumbnail"));
                a.setPostTitle(rs.getString("PostTitle"));
                a.setBriefInformation(rs.getString("BriefInformation"));
                a.setPostDescription(rs.getString("PostDescription"));
                a.setFeatured(rs.getString("Featured"));
                a.setPostDate(rs.getString("PostDate"));
                a.setUpdateDate(rs.getString("UpdateDate"));
                a.setPostStatus(rs.getBoolean("PostStatus"));
                Post_Category pc = new Post_Category();
                pc.setPostCategoryID(rs.getInt("postCategoryID"));
                pc.setPostCategoryName(rs.getString("postCategoryName"));
                a.setPostCategoryID(pc);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }

    /**
     * get the last page number of a blog list
     *
     * @param check
     * @param keyword
     * @param index
     * @return
     */
    public int getBlogEndpage(String check, String keyword, int index) {
        PostDAO postDB = new PostDAO();
        int count = 0;
        // there are 2 choice to choose to check the last page number 
        // if variable @check equals "bloglist", count the last page of the blog list which has @index records per page 
        if (check.equalsIgnoreCase("bloglist")) {
            count = postDB.getCountBlog();
        } // if variable @check equals "searchblog",  count the last page of the blog list searched by @keyword
        else if (check.equalsIgnoreCase("searchBlog")) {
            count = postDB.getCountSearchedBlog(true, keyword);
        }

        int endPage = count / index;
        //if the count number is not divisble by index, variable @endPage + 1 to show residual items 
        if (count % index != 0) {
            endPage++;
        }
        return endPage;

    }

    /**
     * get the last page of blog category list
     *
     * @param index
     * @return
     */
    public int getBlogCategoryEndPage(int index) {
        PostDAO postDB = new PostDAO();
        int count;
        count = postDB.getBlogCategoryCount();
        int endPage = count / index;
        //if the count number is not divisble by index, variable @endPage + 1 to show residual items 
        if (count % index != 0) {
            endPage++;
        }
        return endPage;

    }

    /**
     * to get the blog list searched by category id
     *
     * @param id
     * @param index
     * @return
     */
    public int getBlogListByCategoryEndPage(int id, int index) {
        PostDAO postDB = new PostDAO();
        int count = postDB.getCountBlogByCategory(id);
        int endPage = count / index;
        //if the count number is not divisble by index, variable @endPage + 1 to show residual items 
        if (count % index != 0) {
            endPage++;
        }
        return endPage;
    }

    /**
     * to get the count number of blog category for pagination
     *
     * @return
     */
    public int getBlogCategoryCount() {
        String sql = "select count(*) from [Post_Category] where status = 1";
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            // loop all ResultSet and get the count number of all recors 
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param status
     * @param index
     * @param rows
     * @return
     */
    public List<Post_Category> getPagingBlogCategoryList(boolean status, int index, int records) {
        String sql = "SELECT *\n"
                + "  FROM [Sum22_SWP391_BakeryShop].[dbo].[Post_Category]\n"
                + "  where [status] = ?\n"
                + "  order by PostCategoryName\n"
                + "  offset ? rows fetch next ? rows only ";
        List<Post_Category> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, (index - 1) * records);
            stm.setInt(3, records);
            ResultSet rs = stm.executeQuery();
            // loop all ResultSet and set data for each post 
            while (rs.next()) {

                Post_Category postCate = new Post_Category();
                postCate.setPostCategoryID(rs.getInt("PostCategoryID"));
                postCate.setPostCategoryName(rs.getString("PostCategoryName"));
                postCate.setStatus(rs.getBoolean("status"));
                ls.add(postCate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public int addPost(Post post) {
        int n = 0;
        String sql = "insert into Post(\n"
                + "[UserID]\n"
                + ",[PostCategoryID]\n"
                + ",[Thumbnail]\n"
                + ",[PostTitle]\n"
                + ",[BriefInformation]\n"
                + ",[PostDescription]\n"
                + ",[Featured]\n"
                + ",[PostDate]\n"
                + ",[UpdateDate]\n"
                + ",[PostStatus]) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, post.getUserID().getId());
            stm.setInt(2, post.getPostCategoryID().getPostCategoryID());
            stm.setString(3, post.getThumbnail());
            stm.setString(4, post.getPostTitle());
            stm.setString(5, post.getBriefInformation());
            stm.setString(6, post.getPostDescription());
            stm.setString(7, post.getFeatured());
            stm.setString(8, post.getPostDate());
            stm.setString(9, post.getUpdateDate());
            stm.setBoolean(10, post.isPostStatus());
            n = stm.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int updatePostByID(int id, Post post) {
        int n = 0;
        String sql = "update Post set [PostCategoryID] = ?\n"
                + "      ,[Thumbnail] = ?\n"
                + "      ,[PostTitle]= ?\n"
                + "      ,[BriefInformation] = ?\n"
                + "      ,[PostDescription] = ?\n"
                + "      ,[Featured] = ?\n"
                + "      ,[UpdateDate] = ?\n"
                + "      ,[PostStatus] = ?\n"
                + "	  where postID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, post.getPostCategoryID().getPostCategoryID());
            stm.setString(2, post.getThumbnail());
            stm.setString(3, post.getPostTitle());
            stm.setString(4, post.getBriefInformation());
            stm.setString(5, post.getPostDescription());
            stm.setString(6, post.getFeatured());
            stm.setString(7, post.getUpdateDate());
            stm.setBoolean(8, post.isPostStatus());
            stm.setInt(9, id);
            n = stm.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public List<Post> getListPosts(ResultSet rs) {
        try {
            List<Post> listPosts = new ArrayList<>();
            while (rs.next()) {
                Post p = new Post();
                p.setPostID(rs.getInt("PostId"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setPostTitle(rs.getString("PostTitle"));
                p.setBriefInformation(rs.getString("BriefInformation"));
                p.setPostDescription(rs.getString("PostDescription"));
                p.setFeatured(rs.getString("Featured"));
                p.setPostDate(rs.getString("PostDate"));
                p.setUpdateDate(rs.getString("UpdateDate"));
                p.setPostStatus(rs.getBoolean("PostStatus"));
                Post_Category pc = new Post_Category();
                String sql_cate
                        = "select *from Post_Category where PostCategoryID=?";
                PreparedStatement st = connection.prepareStatement(sql_cate);
                st.setInt(1, rs.getInt("PostCategoryID"));
                ResultSet rs1 = st.executeQuery();
                while (rs1.next()) {
                    Post_Category post_Category = new Post_Category();
                    post_Category.setPostCategoryID(rs1.getInt("PostCategoryID"));
                    post_Category.setPostCategoryName(rs1.getString("PostCategoryName"));
                    post_Category.setStatus(rs1.getBoolean("status"));
                    p.setPostCategoryID(post_Category);
                    break;
                }
                UserDAO userDAO = new UserDAO();
                p.setUserID(userDAO.GetUserProfileById(rs.getInt("UserId")));
                listPosts.add(p);
            }
            return listPosts;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Post> getListPostsByFilter(int page_size, int page_index, String author, String title, int categoryId, int status, String order, String direction) {
        try {
            String sql = "select *from (\n"
                    + "select PostId, a.UserID, name, a.PostCategoryId, Thumbnail, PostTitle, BriefInformation, PostDescription, Featured, PostDate, \n"
                    + "UpdateDate, PostStatus, PostCategoryName, ROW_NUMBER() OVER(order by PostId) as row_index from [Post] as a \n"
                    + "join [Post_Category] as b on a.PostCategoryID = b.PostCategoryID \n"
                    + "join [User] as c on a.UserId = c.Id \n"
                    + "where (1=1)";
            Hashtable<Integer, Object> parameters = new Hashtable<>();
            int index = 1;
            if (!author.equals("")) {
                sql += " and name like ?";
                parameters.put(index, author);
                index += 1;
            }
            if (!title.equals("")) {
                sql += " and PostTitle like ?";
                parameters.put(index, title);
                index += 1;
            }
            if (categoryId != -1) {
                sql += " and a.PostCategoryID = ?";
                parameters.put(index, categoryId);
                index += 1;
            }
            if (status != -1) {
                sql += " and PostStatus = ?";
                parameters.put(index, status);
                index += 1;
            }
            sql += ") as tbl where row_index>=?*(?-1)+1 and row_index<=?*? ";
            if (!order.equals("")) {
                sql += " order by " + order;
            }
            if (!direction.equals("")) {
                sql += " " + direction;
            }
            PreparedStatement st = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                if (value.getClass() == String.class) {
                    st.setString(key, "%" + value + "%");
                } else if (value.getClass() == Integer.class) {
                    st.setInt(key, (Integer) value);
                }
            }
            st.setInt(index, page_size);
            st.setInt(index + 1, page_index);
            st.setInt(index + 2, page_size);
            st.setInt(index + 3, page_index);
            System.out.println(sql);
            return getListPosts(st.executeQuery());
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberOfRecords(String author, String title, int categoryId, int status) {
        try {
            String sql = "select COUNT(*) as total from (select *from (\n"
                    + "select PostId, a.UserID, a.PostCategoryId, Thumbnail, PostTitle, BriefInformation, PostDescription, Featured, PostDate, \n"
                    + "UpdateDate, PostStatus, ROW_NUMBER() OVER(order by PostId) as row_index from [Post] as a \n"
                    + "join [Post_Category] as b on a.PostCategoryID = b.PostCategoryID \n"
                    + "join [User] as c on a.UserId = c.Id \n"
                    + "where (1=1)\n";

            Hashtable<Integer, Object> parameters = new Hashtable<>();
            int index = 1;
            if (!author.equals("")) {
                sql += " and name like ?";
                parameters.put(index, author);
                index += 1;
            }
            if (!title.equals("")) {
                sql += " and PostTitle like ?";
                parameters.put(index, title);
                index += 1;
            }
            if (categoryId != -1) {
                sql += " and a.PostCategoryID = ?";
                parameters.put(index, categoryId);
                index += 1;
            }
            if (status != -1) {
                sql += " and PostStatus = ?";
                parameters.put(index, status);
                index += 1;
            }
            sql += ") as tbl) as t";
            PreparedStatement st = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                if (value.getClass() == String.class) {
                    st.setString(key, "%" + value + "%");
                } else if (value.getClass() == Integer.class) {
                    st.setInt(key, (Integer) value);
                }
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<User> getAllAuthors() {
        try {
            String sql = "select *from [User] where Id in\n"
                    + "(select distinct UserId from [Post])";
            UserDAO userDAO = new UserDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Post_Category> getListPost_Categories() {
        try {
            List<Post_Category> listPost_Categories = new ArrayList<>();
            String sql = "select *from Post_Category";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post_Category post_Category = new Post_Category();
                post_Category.setPostCategoryID(rs.getInt("PostCategoryID"));
                post_Category.setPostCategoryName(rs.getString("PostCategoryName"));
                post_Category.setStatus(rs.getBoolean("Status"));
                listPost_Categories.add(post_Category);
            }
            return listPost_Categories;
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int UpdateStatus(int sId, int status) {
        int n = 0;
        try {
            String sql = "UPDATE [Post]\n"
                    + "SET [PostStatus] = ?\n"
                    + "WHERE [PostId] = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, sId);
            statement.setInt(1, status);
            n = statement.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int getLatestPostID() {
        int n = 0;
        try {
            String sql = "SELECT TOP (1) postid \n"
                    + "from post\n"
                    + "order by postid desc ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                n = rs.getInt("postid");
            }
        } catch (Exception e) {
        }
        return n;
    }
     public int getCountPost() {
        int n = 0;
        try {
            String sql = "select count(*) from Post  ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountPostInRange(String before, String then) {
        int n = 0;
        try {
            String sql = "select count(*) from Post where PostDate  between ? and ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, before);
            stm.setString(2, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountPostInRange(String status, String before, String then) {
        int n = 0;
        try {
            String sql = "select count(*) from Post where PostStatus = ? and PostDate  between ? and ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, before);
            stm.setString(3, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        PostDAO p = new PostDAO();
//        List<Post> listPosts = p.getListPostsByFilter(5, 1, "", "", 1, -1, "PostTitle", "desc");
//        for (Post post : listPosts) {
//            System.out.println("-----------------------------------------");
//            System.out.println(post);
//        }
        System.out.println(p.getCountPost());
    }

}
