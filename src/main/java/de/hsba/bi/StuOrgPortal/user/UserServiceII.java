package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.web.error.UserAlreadyExistException;
import de.hsba.bi.StuOrgPortal.web.user.dto.UserDto;


public interface UserServiceII {

    User createNewStudent(UserDto userDto) throws UserAlreadyExistException;

    User createNewLecturer(UserDto userDto) throws UserAlreadyExistException;

}
