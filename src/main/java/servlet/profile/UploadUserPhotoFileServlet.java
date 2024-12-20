package servlet.profile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dto.user.UserEditDto;
import helpers.CloudinaryHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UploadUserPhotoFileServlet extends HttpServlet {

    private final Cloudinary cloudinary = CloudinaryHelper.getInstance();
    private static final String FILE_PREFIX = "/tmp";
    private static final int DIRECTORIES_COUNT = 10;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UserEditDto user = (UserEditDto) session.getAttribute("user");

        Long id = user.getId();
        String login = user.getLogin();
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");

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

        String oldUrl = userService.getPhotoUrl(login);

        if (oldUrl != null) {
            cloudinary.uploader().destroy(oldUrl, ObjectUtils.emptyMap());
        }

        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        String photoUrl = (String) uploadResult.get("url");

        userService.savePhotoUrl(photoUrl, id);
        session.setAttribute("user", userService.getUserEditDto(login));

        resp.sendRedirect("/profile");
    }
}