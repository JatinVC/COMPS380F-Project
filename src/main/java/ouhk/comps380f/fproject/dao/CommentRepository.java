/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.util.Date;
import java.util.List;

import ouhk.comps380f.fproject.model.Comments;

/**
 *
 * @author Jatin
 */
public interface CommentRepository {
    public long createComment(long itemId, String comment);

    public List<Comments> getComments(long itemId);

    public Comments getComment(long id);

    public void updateComment(long id, String comment);

    public void deleteComment(long id);
}
