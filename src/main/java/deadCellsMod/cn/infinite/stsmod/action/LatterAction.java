package deadCellsMod.cn.infinite.stsmod.action;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class LatterAction
        extends AbstractGameAction {
    private Runnable action;
    private boolean isRun;

    public LatterAction(Runnable action) {
        this(action, 0.0F);
    }

    public LatterAction(Runnable action, float lateTime) {
        this.action = action;
        this.duration = lateTime;
        this.isRun = false;
    }

    public void update() {
        if(this.duration>0.0F && this.action != null && !this.isRun){
            this.action.run();
            this.isRun = true;
        }else if(this.duration <= 0.0F) {
            this.isDone = true;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
    }
}

