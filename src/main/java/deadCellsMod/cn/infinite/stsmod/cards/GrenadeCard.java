package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.UseTheSameCardAgainAction;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

public abstract class GrenadeCard extends DeadCellsCard {

    public GrenadeCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.tags.add(DeadCellsTags.GRENADE);
        this.rawDescription += " NL *被丢弃时自动打出。";
        this.initializeDescription();
    }

    public GrenadeCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.tags.add(DeadCellsTags.GRENADE);
        this.rawDescription += " NL *被丢弃时自动打出。";
        this.initializeDescription();
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public AbstractCard makeCopy() {
        return super.makeCopy();
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new UseTheSameCardAgainAction(this, AbstractDungeon.player,null));
    }
}
