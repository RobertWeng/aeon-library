package com.aeon.library.model.dto.response;

import java.util.List;

public record ResultList<T>(long total, List<T> result) {

}