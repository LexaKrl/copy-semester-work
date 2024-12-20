package servlet.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import helpers.CloudinaryHelper;
import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.PostService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet(urlPatterns = "/upload-post-photo")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UploadPhotoPostServlet extends HttpServlet {

    private final Cloudinary cloudinary = CloudinaryHelper.getInstance();
    private static final String FILE_PREFIX = "/tmp";
    private static final int DIRECTORIES_COUNT = 10;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = (PostService) this.getServletContext().getAttribute("postService");

        Long postId = IdParsingHelper.parseId(req.getParameter("postId"));
        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));
        Long teamId = IdParsingHelper.parseId(req.getParameter("teamId"));

        Part part = req.getPart("file");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File("%s%s%s%s%s".formatted(FILE_PREFIX, File.separator, filename.hashCode() % DIRECTORIES_COUNT,
                File.separator, filename));

        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();

        String oldUrl = postService.getPhotoUrl(postId);

        if (oldUrl != null) {
            cloudinary.uploader().destroy(oldUrl, ObjectUtils.emptyMap());
        }

        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        String photoUrl = (String) uploadResult.get("url");

        postService.savePhotoUrl(photoUrl, postId);

        resp.sendRedirect("/team/%s/project/%s/post/%s".formatted(teamId, projectId, postId));
    }
}
