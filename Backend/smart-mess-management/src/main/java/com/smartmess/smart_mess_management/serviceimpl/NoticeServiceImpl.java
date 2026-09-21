package com.smartmess.smart_mess_management.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.NoticeService;
import com.smartmess.smart_mess_management.entity.Notice;
import com.smartmess.smart_mess_management.repository.NoticeRepository;

import enums.NoticeType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notice not found with id: " + id));
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice updateNotice(Long id, Notice notice) {

        Notice existing = getNoticeById(id);

        existing.setTitle(notice.getTitle());
        existing.setContent(notice.getContent());
        existing.setType(notice.getType());
        existing.setAdmin(notice.getAdmin());
        existing.setValidUntil(notice.getValidUntil());
        existing.setIsActive(notice.getIsActive());

        return noticeRepository.save(existing);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public List<Notice> getActiveNotices() {
        return noticeRepository.findByIsActiveTrue();
    }

    @Override
    public List<Notice> getNoticesByType(NoticeType type) {
        return noticeRepository.findByType(type);
    }
}