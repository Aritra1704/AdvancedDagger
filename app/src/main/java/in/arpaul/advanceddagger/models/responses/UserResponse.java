package in.arpaul.advanceddagger.models.responses;

import java.util.ArrayList;

import in.arpaul.advanceddagger.models.BaseDO;

public class UserResponse extends BaseDO {

    public UserResponse(
            String _id,
            String first_name,
            String user_type,
            String username,
            String x_zippr_sessiontoken,
            ArrayList<String> project_ids,
            ArrayList<Project_Style_Id> project_style_ids
    ) {
    }

    public class Project_Style_Id extends BaseDO {
        public Project_Style_Id(
                String project_name,
                String road_map_style_id,
                String street_map_style_id
        ) {
        }
    }
}
