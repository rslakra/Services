package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.FileStorageConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FileStorageConstantsImpl implements FileStorageConstants {

    @Value("${filestor.path.financial_report}")
    private String financialReportPath;

    @Value("${filestor.path.monthly_report}")
    private String monthlyReportsBasePath;

    @Value("${external.pic.url.allow_list}")
    private String[] externalPicUrlsAllowList;
}
