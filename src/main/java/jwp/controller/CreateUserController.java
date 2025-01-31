package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CreateUserController extends AbstractController {
    UserDao userDao = new UserDao();

    HttpSession session;
    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));

        userDao.insert(user);
        return jspView("redirect:/user/list");
    }
}
