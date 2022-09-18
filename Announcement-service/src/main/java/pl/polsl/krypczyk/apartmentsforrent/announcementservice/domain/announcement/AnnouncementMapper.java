package pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcement;

import org.mapstruct.Mapper;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement.dto.AnnouncementDTO;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement.request.AddNewAnnouncementRequest;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement.request.UpdateAnnouncementRequest;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement.response.AddNewAnnouncementResponse;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcement.response.UpdateAnnouncementResponse;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.domain.announcementdetails.AnnouncementDetailsEntity;
import pl.polsl.krypczyk.apartmentsforrent.announcementservice.application.announcementdetails.dto.AnnouncementDetailsDTO;

@Mapper
public interface AnnouncementMapper {
    AnnouncementDetailsDTO announcementDetailsEntityToAnnouncementDetailsDTO(AnnouncementDetailsEntity announcementDetailsEntity);
    AnnouncementDTO announcementEntityToAnnouncementDTO(AnnouncementEntity announcementEntity);
    AddNewAnnouncementResponse createAnnouncementRequestToCreateAnnouncementResponse(AddNewAnnouncementRequest addNewAnnouncementRequest);
    UpdateAnnouncementResponse updateAnnouncementRequestToUpdateAnnouncementResponse(UpdateAnnouncementRequest updateAnnouncementRequest);
}
