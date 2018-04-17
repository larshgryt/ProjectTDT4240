package com.mygdx.game.testing;

import com.mygdx.game.components.Component;
import com.mygdx.game.handlers.collision.BoundingBox;

import static org.mockito.Mockito.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

/**
 * Created by El Perozo on 17.04.2018.
 */
public class BoundingBoxTest extends GameTest{
    @Mock
    Component cp1;
    Component cp2;
    @Test
    public void overlaps() throws Exception {
        cp1.setPosition(2,3);
        cp2.setPosition(3,4);
        BoundingBox bb1 = new BoundingBox(cp1);
        BoundingBox bb2 = new BoundingBox(cp2);
        boolean output = bb1.overlaps(bb2);
        assertEquals(false, output);

    }

}