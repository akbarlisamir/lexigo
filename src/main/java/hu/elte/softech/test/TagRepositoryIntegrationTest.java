package hu.elte.softech.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import hu.elte.softech.controller.TagController;
import hu.elte.softech.entity.Tag;


public class TagRepositoryIntegrationTest {
	
	@Test
    public void testGetTagById() {
        TagController tagController = new TagController();
        String tagName = tagController.findOneTag(155).getValue();
        assertEquals("World", tagName);
    }

}
