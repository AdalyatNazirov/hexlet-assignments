package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// BEGIN
@Getter
@AllArgsConstructor
public class UsersPage {
    private List<User> users;
}
// END
