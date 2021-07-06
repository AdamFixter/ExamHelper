package me.B9038462.ExamHelper.ExamHelperApp.Infrastructure.Application.Controllers.Scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

public class PermissionScene extends Scene {
    private ScenePermission permission;

    public PermissionScene(Parent parent) {
        super(parent);
    }

    public PermissionScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public PermissionScene(Parent parent, Paint paint) {
        super(parent, paint);
    }

    public PermissionScene(Parent parent, double v, double v1, Paint paint) {
        super(parent, v, v1, paint);
    }

    public PermissionScene(Parent parent, double v, double v1, boolean b) {
        super(parent, v, v1, b);
    }

    public PermissionScene(Parent parent, double v, double v1, boolean b, SceneAntialiasing sceneAntialiasing) {
        super(parent, v, v1, b, sceneAntialiasing);
    }
}
