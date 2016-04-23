package org.vaadin.liveimageeditor;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.communication.URLReference;
import com.vaadin.ui.AbstractJavaScriptComponent;
import org.vaadin.liveimageeditor.client.LiveImageEditorState;

import java.io.InputStream;
import java.io.OutputStream;

@JavaScript({"jquery-1.12.3.min.js", "jquery.mousewheel.js", "live-image-editor.js"})
@StyleSheet("live-image-editor.css")
public class LiveImageEditor extends AbstractJavaScriptComponent {

    private static final String IMAGE_KEY = "image-key";

    public void setImage(final InputStream inputStream) {
        String imageFileName = "image-file-name";
        StreamResource resource = new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                return inputStream;
            }
        }, imageFileName);

        setResource(IMAGE_KEY, resource);
        getState().imageUrl = getResourceUrl(IMAGE_KEY);
    }

    private String getResourceUrl(String key) {
        URLReference urlReference = getState().resources.get(key);
        if (urlReference == null) {
            return null;
        } else {
            return urlReference.getURL();
        }
    }

    @Override
    public LiveImageEditorState getState() {
        return (LiveImageEditorState) super.getState();
    }

    public OutputStream getEditedImage() {
        return null;
    }

}
