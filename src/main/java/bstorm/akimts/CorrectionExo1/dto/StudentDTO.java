package bstorm.akimts.CorrectionExo1.dto;

import bstorm.akimts.CorrectionExo1.constraints.OldTimeResults;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
@OldTimeResults
public class StudentDTO implements IdentifiedDTO<Integer> {
    @NotNull
    Integer id;
    @Size(min = 2, max = 20)
    String firstname;
    @NotBlank // @NotEmpty
    String lastname;
    @NotNull
    @PastOrPresent // @Past // @Future // @FuturOrPresent
    LocalDateTime birthdate;
    //@Pattern(regexp = "")
    //@Email
    String login;
    SmallSectionDTO section;
    @NotNull
    @Min(0) @Max(20)
    // @Positive // @Negative // NegativeOrZero // PositiveOrZero
    Integer result;
    @NotNull
    String courseId;

//    @AssertFalse // @AssertTrue
//    private boolean bool;
}
