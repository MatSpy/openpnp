package org.openpnp.gui.components.nav;

import java.awt.image.BufferedImage;

import org.openpnp.CameraListener;
import org.openpnp.model.Configuration;
import org.openpnp.model.LengthUnit;
import org.openpnp.model.Location;
import org.openpnp.spi.Camera;
import org.openpnp.spi.Head;
import org.openpnp.spi.Machine;
import org.openpnp.spi.MachineListener;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

class CameraImageView extends ImageView implements CameraListener {
    final Camera camera;
    
    public CameraImageView(Camera camera) {
        this.camera = camera;
        
        Location unitsPerPixel =
                camera.getUnitsPerPixel().convertToUnits(LengthUnit.Millimeters);
        double width = unitsPerPixel.getX() * camera.getWidth();
        double height = unitsPerPixel.getY() * camera.getHeight();
        setFitWidth(width);
        setFitHeight(height);
        // Images are flipped with respect to display coordinates, so
        // flip em back.
        setScaleY(-1);
        setTranslateX(-width / 2);
        setTranslateY(-height / 2);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                setOpacity(getOpacity() == 1 ? 0.20 : 1);
            }
        });
        camera.startContinuousCapture(this, 10);
        
        updateLocation();
        
        Configuration.get().getMachine().addListener(machineListener);
    }
    
    private void updateLocation() {
        Location location = camera.getLocation().convertToUnits(LengthUnit.Millimeters);
        setX(location.getX());
        setY(location.getY());
    }
    

    public void frameReceived(BufferedImage img) {
        setImage(SwingFXUtils.toFXImage(img, null));
    }
    
    MachineListener machineListener = new MachineListener.Adapter() {
        @Override
        public void machineHeadActivity(Machine machine, Head head) {
            Platform.runLater(() -> updateLocation());
        }

        @Override
        public void machineEnabled(Machine machine) {
            Platform.runLater(() -> updateLocation());
        }
    };    
}