package ifce.polo.sippi.controller;

import static ifce.polo.sippi.util.StringUtils.concat;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class AbstractController
{
    @Autowired protected ObjectMapper jsonMapper;

/* --------------------------------------------------------------------------------------------- */

    protected ObjectNode json() {
        return jsonMapper.createObjectNode();
    }

/* --------------------------------------------------------------------------------------------- */

    protected ObjectNode success() {
        return json().put("status", "success");
    }

/* --------------------------------------------------------------------------------------------- */

    protected ObjectNode failed() {
        return json().put("status", "failed");
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<?> validationFailed(ObjectNode error) {
        return ok(failed().set("rejected", error));
    }

/* --------------------------------------------------------------------------------------------- */

    protected ObjectNode error(String description) {
        return json().put("error", description);
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<?> ok() {
        return status(HttpStatus.OK).build();
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<?> ok(Object body) {
        return status(HttpStatus.OK).body(body);
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<?> created(Object id)
    {
        ObjectNode body = success();

        if (id != null) {
            body.put("id", id.toString());
        }

        return status(HttpStatus.CREATED).body(body);
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<ObjectNode> badRequest(String description) {
        return status(HttpStatus.BAD_REQUEST).body(error(description));
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<ObjectNode> unauthorized(String description) {
        return status(HttpStatus.UNAUTHORIZED).body(error(description));
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<ObjectNode> forbidden(String description) {
        return status(HttpStatus.FORBIDDEN).body(error(description));
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<ObjectNode> idNotFound(Object id) {
        return status(HttpStatus.NOT_FOUND).body(error(concat("ID ", id, " not found")));
    }

/* --------------------------------------------------------------------------------------------- */

    protected ResponseEntity<ObjectNode> notFound(String description) {
        return status(HttpStatus.NOT_FOUND).body(error(description));
    }

/* --------------------------------------------------------------------------------------------- */

}
