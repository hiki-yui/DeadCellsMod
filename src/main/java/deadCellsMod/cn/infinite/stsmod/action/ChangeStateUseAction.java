package deadCellsMod.cn.infinite.stsmod.action;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ChangeStateUseAction extends AbstractGameAction {
    private AbstractCreature owner;
    private String oldState;
    private String newState;
    private boolean oldLoop;
    private boolean newLoop;
    private boolean canReturn;
    private boolean update = false;
    private float timeScale = 1.0F;

    public ChangeStateUseAction(AbstractCreature owner, String newState, boolean newloop, float timeScale, float duration) {
        this.owner = owner;
        this.newState = newState;
        this.newLoop = newloop;
        this.canReturn = false;
        this.duration = duration;
        this.timeScale = timeScale;
    }

    public ChangeStateUseAction(AbstractCreature owner, String oldState, String newState, boolean oldLoop, boolean newLoop, float timeScale, float duration, boolean canReturn) {
        this.owner = owner;
        this.oldState = oldState;
        this.newState = newState;
        this.oldLoop = oldLoop;
        this.newLoop = newLoop;
        this.canReturn = canReturn;
        this.duration = duration;
        this.timeScale = timeScale;
    }

    public void update() {
        if (!this.isDone) {
            if (!this.update) {
                if (!this.canReturn) {
                    AnimationState.TrackEntry e = this.owner.state.setAnimation(0, this.newState, this.newLoop);
                    e.setTimeScale(this.timeScale);
                } else {
                    AnimationState.TrackEntry e = this.owner.state.setAnimation(0, this.newState, this.newLoop);
                    this.owner.state.addAnimation(0, this.oldState, this.oldLoop, 0.0F);
                    e.setTimeScale(this.timeScale);
                }
                this.update = true;
            }
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration <= 0.0F)
                this.isDone = true;
        }
    }
}