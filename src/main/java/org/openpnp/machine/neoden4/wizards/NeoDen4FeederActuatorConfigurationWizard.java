/*
 * Copyright (C) 2011 Jason von Nieda <jason@vonnieda.org>
 * 
 * This file is part of OpenPnP.
 * 
 * OpenPnP is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * OpenPnP is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with OpenPnP. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 * For more information about OpenPnP visit http://openpnp.org
 */

package org.openpnp.machine.neoden4.wizards;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.openpnp.gui.components.ComponentDecorators;
import org.openpnp.gui.support.DriversComboBoxModel;
import org.openpnp.gui.support.IntegerConverter;
import org.openpnp.gui.support.NamedConverter;
import org.openpnp.machine.neoden4.NeoDen4FeederActuator;
import org.openpnp.machine.reference.wizards.AbstractActuatorConfigurationWizard;
import org.openpnp.model.Configuration;
import org.openpnp.spi.Driver;
import org.openpnp.spi.base.AbstractMachine;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class NeoDen4FeederActuatorConfigurationWizard extends AbstractActuatorConfigurationWizard {
    private JPanel panelProperties;
    private JLabel lblName;
    private JTextField nameTf;
    private JLabel lblDriver;
    private JComboBox driver;
    
    private JLabel lblPeelerId;
    private JTextField PeelerIdTextField;
    
    private JLabel lblFeederId;
    private JTextField FeederIdTextField;
    
    private JLabel lblPeelStrength;
    private JTextField PeelStrengthTextField;
    
    private JLabel lblFeedStrength;
    private JTextField FeedStrengthTextField;
    
    private JLabel lblFeedLength;
    private JTextField FeedLengthTextField;
    
    private JLabel lblPeelLength;
    private JTextField PeelLengthTextField;
    
    public NeoDen4FeederActuatorConfigurationWizard(AbstractMachine machine, NeoDen4FeederActuator actuator) {
        super(machine,  actuator);
    }
        

    
    
    @Override 
    protected void createUi(AbstractMachine machine) {
        panelProperties = new JPanel();
        panelProperties.setBorder(new TitledBorder(null, "Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panelProperties);
        panelProperties.setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(70dlu;default)"),
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,  
        		},
            new RowSpec[] {
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
                FormSpecs.RELATED_GAP_ROWSPEC,
                FormSpecs.DEFAULT_ROWSPEC,
        }));
        
        lblDriver = new JLabel("Driver");
        panelProperties.add(lblDriver, "2, 2, right, default");
        
        driver = new JComboBox(new DriversComboBoxModel(machine, true));
        panelProperties.add(driver, "4, 2, fill, default");
        
        lblName = new JLabel("Name");
        panelProperties.add(lblName, "2, 4, right, default");
        
        nameTf = new JTextField();
        panelProperties.add(nameTf, "4, 4, fill, default");
        nameTf.setColumns(20);
        
        
        //===================
        
        lblPeelerId = new JLabel("Peeler ID");
        panelProperties.add(lblPeelerId, "2, 6, right, default");
        
        PeelerIdTextField = new JTextField();
        panelProperties.add(PeelerIdTextField, "4, 6, fill, default");
        PeelerIdTextField.setColumns(16);
        
        lblFeederId = new JLabel("Feeder ID");
        panelProperties.add(lblFeederId, "2, 8, right, default");
        
        FeederIdTextField = new JTextField();
        panelProperties.add(FeederIdTextField, "4, 8, fill, default");
        FeederIdTextField.setColumns(16);     
        
        lblFeedStrength = new JLabel("Feed Strength");
        panelProperties.add(lblFeedStrength, "2, 10, right, default");
        
        FeedStrengthTextField = new JTextField();
        panelProperties.add(FeedStrengthTextField, "4, 10, fill, default");
        FeedStrengthTextField.setColumns(16);     
     
        lblPeelStrength = new JLabel("Peel Strength");
        panelProperties.add(lblPeelStrength, "2, 12, right, default");
        
        PeelStrengthTextField = new JTextField();
        panelProperties.add(PeelStrengthTextField, "4, 12, fill, default");
        PeelStrengthTextField.setColumns(16);
        
        lblFeedLength = new JLabel("Feed length");
        panelProperties.add(lblFeedLength, "2, 14, right, default");
        
        FeedLengthTextField = new JTextField();
        panelProperties.add(FeedLengthTextField, "4, 14, fill, default");
        FeedLengthTextField.setColumns(16);
        
        lblPeelLength = new JLabel("Peel length");
        panelProperties.add(lblPeelLength, "2, 16, right, default");
        
        PeelLengthTextField = new JTextField();
        panelProperties.add(PeelLengthTextField, "4, 16, fill, default");
        PeelLengthTextField.setColumns(16);
        
        super.createUi(machine);
    }

    @Override
    public void createBindings() {
        super.createBindings();
        
        IntegerConverter intConverter = new IntegerConverter();
        AbstractMachine machine = (AbstractMachine) Configuration.get().getMachine();
        NamedConverter<Driver> driverConverter = new NamedConverter<>(machine.getDrivers()); 
        
        addWrappedBinding(actuator, "driver", driver, "selectedItem", driverConverter);
        addWrappedBinding(actuator, "name", nameTf, "text");
        
        addWrappedBinding(actuator, "peelerId", PeelerIdTextField, "text", intConverter);
        addWrappedBinding(actuator, "feederId", FeederIdTextField, "text", intConverter);
        addWrappedBinding(actuator, "feedStrength", FeedStrengthTextField, "text", intConverter);
        addWrappedBinding(actuator, "peelStrength", PeelStrengthTextField, "text", intConverter);
        addWrappedBinding(actuator, "feedLength", FeedLengthTextField, "text", intConverter);
        addWrappedBinding(actuator, "peelLength", PeelLengthTextField, "text", intConverter);
        
        ComponentDecorators.decorateWithAutoSelect(nameTf);
    }
}
