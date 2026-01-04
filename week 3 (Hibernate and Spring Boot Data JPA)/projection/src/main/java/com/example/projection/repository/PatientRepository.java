    package com.example.projection.repository;

    import com.example.projection.dto.BloodGroupStats;
    import com.example.projection.dto.CPatientInfo;
    import com.example.projection.dto.IPatientInfo;
    import com.example.projection.entity.Patient;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;

    public interface  PatientRepository extends JpaRepository<Patient,Long> {
        @Query("""
        select 
            p.id as id,
            p.name as name,
            p.email as email
        from Patient p
    """)
        List<IPatientInfo> getAllPatientInfo();

        @Query("select new com.example.projection.dto.CPatientInfo(p.id,p.name)" +"from Patient p")
        List<CPatientInfo> getAllPatientInfoConcrete();

        @Query("""
    select new com.example.projection.dto.BloodGroupStats(
        p.bloodGrpType,
        count(p)
    )
    from Patient p
    group by p.bloodGrpType
    order by count(p) DESC
""")
        List<BloodGroupStats> getBloodGroupStats();
    }
