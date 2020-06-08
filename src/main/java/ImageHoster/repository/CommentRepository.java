package ImageHoster.repository;


import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment addNewComment(Comment newComment){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }

    public List<Comment> getAllComments(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i where i.image=:image", Comment.class);
        List<Comment> resultList = query.getResultList();
        return resultList;
    }

    public Comment getCommentByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT i from Comment i where i.title =:title",
                    Comment.class).setParameter("title", title);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Comment getComment(Integer commentId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> typedQuery = em.createQuery("SELECT i from Comment i where i.id =:commentId", Comment.class).setParameter("commentId", commentId);
        Comment comment = typedQuery.getSingleResult();
        return comment;
    }

    public void updateComment(Comment updatedComment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(updatedComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deleteComment(Integer commentId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Comment comment = em.find(Comment.class,commentId);
            em.remove(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }



}
