package com.ispirer.hacker.utlis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListOfDto<T> {
    private List<T> content;
    private Integer size;
}
