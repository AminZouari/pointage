package stage.pointage.repository;



import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stage.pointage.domain.PresenceList;

@Repository

public interface PresenceListRepository extends JpaRepository<PresenceList, Long> {
	
	@Query(value = "select * from presence_list where jour=? and presence=0.0",nativeQuery=true)
	List<PresenceList>  getAbsenceList(String jour);
	
	@Query(value = "select * from presence_list where jour=? and presence>0",nativeQuery=true)
	List<PresenceList>  getPresenceList(String jour);
	
	@Query(value = "select * from presence_list where jour=? ",nativeQuery=true)
	List<PresenceList>  getList(String jour);
	
	@Modifying
	@Transactional
	@Query(value="insert into presence_list (id,cin,nom,jour,presence) values(?,?,?,?,?)",nativeQuery=true)
	void insertIntoPresenceList(Long id,String cin,String nom,String jour,float presence);
	
	
	@Modifying
    @Transactional
@Query(value = "update presence_list set presence=? where id =? ",nativeQuery=true)
void edit( float a , Long b);
	
	
	@Query
	(value="select * from presence_list where  cin=? and jour=?", nativeQuery=true)
	PresenceList verif(String d, String a);
	
	

};
