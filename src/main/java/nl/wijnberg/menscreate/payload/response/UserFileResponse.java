package nl.wijnberg.menscreate.payload.response;

import nl.wijnberg.menscreate.domain.User;

import java.util.List;

//todo: later check if createBookingResponse list method works for user and files same way
// and implement in userController in findUserByToken or with builderpattern..

public class UserFileResponse {
    private User user;
    private List<FileResponse> userFiles;

    public UserFileResponse(User user, List<FileResponse> userFiles) {
        this.user = user;
        this.userFiles = userFiles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FileResponse> getUserFiles() {
        return userFiles;
    }

    public void setUserFiles(List<FileResponse> userFiles) {
        this.userFiles = userFiles;
    }
}
