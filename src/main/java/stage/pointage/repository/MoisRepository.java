package stage.pointage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stage.pointage.domain.Mois_state;
@Repository
public interface MoisRepository extends JpaRepository<Mois_state, String> {
	@Query(value= "select id,cin,nom,substring(str_to_date(concat('01/',mois),'%d/%m/%Y'),1,7) as mois,presence from mois_state order by mois desc " , nativeQuery = true)
	List<Mois_state> find();
	
	@Query(value="select * from mois_state where mois=?",nativeQuery=true)
	List<Mois_state> getMonthList(String month);
	
	@Query(value="select * from mois_state where cin=? and mois=?",nativeQuery=true)
	Mois_state verif(String month,String cin);
	@Modifying
    @Transactional
	@Query
	(value="update mois_state set presence=presence+?  where cin=? and mois=?", nativeQuery=true)
	void updateMonth(float z,String d, String a);
	@Modifying
    @Transactional
    @Query
    (value="insert into mois_state (id,cin,nom,mois,presence) values (?,?,?,?,?)",nativeQuery=true)
	void insertMonth(Long a ,String cin,String nom,String mois,float presenceSum)	;
	

}
