package com.webapp.tp2_lab_javareact.repository;

import com.webapp.tp2_lab_javareact.entity.LaboralConcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface LaboralConceptRepository extends JpaRepository<LaboralConcept, Long> {
    /**
     * Finds labor concepts in the repository that contain the provided name.
     * The search is case-insensitive.
     *
     * @param name The name of the labor concept (can be a partial name).
     * @return A list of {@link LaboralConcept} objects that contain the provided name.
     */
    List<LaboralConcept> findByNameIgnoreCaseContaining(String name);

    /**
     * Finds a labor concept by id in the repository.
     * If the labor concept with the provided id is not found, an empty list is returned.
     *
     * @param id The id of the labor concept.
     * @return A list containing the labor concept with the provided id if it exists, or an empty list if it does not.
     */
    default List<LaboralConcept> findLaboralConceptById(Long id) {
        return this.findById(id)
                .map(Collections::singletonList)
                .orElseGet(Collections::emptyList);
    }

    /**
     * Finds labor concepts in the repository that match the provided id and name.
     * The name search is case-insensitive.
     *
     * @param id   The id of the labor concept.
     * @param name The name of the labor concept (can be a partial name).
     * @return A list of {@link LaboralConcept} objects that match the provided id and name.
     */
    List<LaboralConcept> findByIdAndNameContainingIgnoreCase(Long id, String name);
}
