package pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.AnnouncementService;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.dto.AnnouncementDTO;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.request.AddNewAnnouncementRequest;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.request.UpdateAnnouncementRequest;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.response.AddNewAnnouncementResponse;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.response.GetAnnouncementWithAllDetailsResponse;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement.response.UpdateAnnouncementResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/announcement/api/v1")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/public/announcements")
    public Collection<AnnouncementDTO> getAllActiveAnnouncements() {
        return this.announcementService.getAllActiveAnnouncements();
    }

    @GetMapping("/public/announcements/{announcementId}")
    public GetAnnouncementWithAllDetailsResponse getAnnouncementWithAllDetails(@NotNull @PathVariable("announcementId") Long announcementId) {
        return this.announcementService.getAnnouncementWithAllDetails(announcementId);
    }

    @PostMapping("/announcements")
    public AddNewAnnouncementResponse addNewAnnouncement(@RequestBody @Valid AddNewAnnouncementRequest addNewAnnouncementRequest) {
        return this.announcementService.addNewAnnouncement(addNewAnnouncementRequest);
    }

    @PutMapping("/announcements/{announcementId}")
    public UpdateAnnouncementResponse updateAnnouncement(@RequestBody @Valid UpdateAnnouncementRequest updateAnnouncementRequest,
                                                         @PathVariable("announcementId") @NotNull Long announcementId,
                                                         @RequestHeader("requester-user-id") @NotNull Long requesterId) {
        return this.announcementService.updateAnnouncement(updateAnnouncementRequest, announcementId, requesterId);
    }


}
