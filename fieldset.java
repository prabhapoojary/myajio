/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

public class fieldset implements Serializable{

private static final long serialVersionUID = 1L;

private String id;
private String name;
/**
 * @return the id
 */
public String getId() {
    return id;
}
/**
 * @param id the id to set
 */
public void setId(String id) {
    this.id = id;
}
/**
 * @return the name
 */
public String getName() {
    return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
    this.name = name;
}
}