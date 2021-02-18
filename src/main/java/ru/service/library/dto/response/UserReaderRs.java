package ru.service.library.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.service.library.dto.UserDTO;
import ru.service.library.dto.Status;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReaderRs {

    @JsonProperty("status")
    private Status status;

    @JsonProperty("user")
    private List<UserDTO> userReaders;


}
