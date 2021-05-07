/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.springframework.jdbc.core.support.SqlLobValue;

import ouhk.comps380f.fproject.model.Attachment;

/**
 *
 * @author Jatin
 */
public interface PictureRepository {
    public long createPicture(String pictureName, String mimetype, long itemId, InputStream blob);

    public List<Attachment> getAttachments(long itemId);

    public List<Attachment> getAttachment(long id, long itemId);

    public void updateAttachment(long itemId, long id, String pictureName, String mimetype, InputStream blob);

    public void deleteAttachment(long itemId, long id);
}