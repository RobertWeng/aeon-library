package com.aeon.library.repo;

import com.aeon.library.model.dto.request.FindByKeywordReq;
import com.aeon.library.model.entity.Borrower;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Long>, BorrowerBuilderRepo {
    Optional<Borrower> findFirstByEmail(String email);
}

interface BorrowerBuilderRepo {
    List<Borrower> findAll(FindByKeywordReq req);
}

class BorrowerBuilderRepoImpl implements BorrowerBuilderRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Borrower> findAll(FindByKeywordReq req) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM Borrower o ");
        sql.append("WHERE 1 = 1 ");
        if (isNotBlank(req.getKeyword())) {
            sql.append("AND (");
            sql.append("(LOWER(o.email)) LIKE :keyword ");
            sql.append("OR LOWER(o.name) LIKE :keyword");
            sql.append(")");
        }
        TypedQuery<Borrower> query = em.createQuery(sql.toString(), Borrower.class);
        if (isNotBlank(req.getKeyword())) {
            query.setParameter("keyword", "%" + req.getKeyword() + "%");
        }
        return query.getResultList();
    }
}

