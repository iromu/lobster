package lobster.server.rest.service;

import lobster.persistence.jpa.repository.StatusRepository;
import lobster.persistence.model.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by wantez on 07/12/13.
 */
@Service
@Transactional
public class StatusService {

    @Inject
    private StatusRepository statusRepository;

    public Status findByLobsterId(Long lobsterId) {
        return statusRepository.findOne(lobsterId);
    }
}
