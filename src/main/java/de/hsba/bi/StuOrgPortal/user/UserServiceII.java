package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.web.error.UserAlreadyExistException;

public interface UserServiceII {

    User createNewStudent(User user) throws UserAlreadyExistException;

    User createNewLecturer(User user) throws UserAlreadyExistException;

}
