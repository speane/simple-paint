package drawing.shapes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Speane on 26.03.2016.
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public interface Shape {
}
