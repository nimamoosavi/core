package app.ladderproject.core.aop;

import app.ladderproject.core.anotations.Unauthorized;

public interface UnauthorizedService {

    void unauthorized(Unauthorized unauthorized);
}
