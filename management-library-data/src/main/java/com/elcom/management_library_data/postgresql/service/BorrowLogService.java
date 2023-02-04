
package com.elcom.management_library_data.postgresql.service;

import com.elcom.management_library_data.mysql.dto.Response;
import com.elcom.management_library_data.postgresql.dto.BorrowLogDto;
import com.elcom.management_library_data.postgresql.dto.BorrowLogNameWithTime;
import com.elcom.management_library_data.postgresql.dto.BorrowLogWithTime;
import java.util.List;


public interface BorrowLogService {
    Response createBorrowLog(BorrowLogDto borrowLogDto);
    Response updateBorrowLog(Long id, BorrowLogDto borrowLogDto);
    BorrowLogWithTime countBorrowLogWithTime(BorrowLogWithTime borrowLogWithTime);
    List<BorrowLogNameWithTime> maxBorrowLogBook(BorrowLogNameWithTime borrowLogNameWithTime);
    List<BorrowLogDto> getAllBorrowLog();
}
