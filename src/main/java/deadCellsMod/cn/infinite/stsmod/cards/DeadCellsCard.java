package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class DeadCellsCard extends CustomCard {

    public int burnNumber;
    public int baseBurnNumber;
    public boolean burnUpGraded = false;
    public int ammunitionNumber;
    public int maxAmmunitionNumber;
    public boolean ammunitionUpgraded = false;
    public int baseHeavyDamage;
    public int heavyDamage;
    public boolean heavyDamageUpgraded = false;
    public int baseChangeNum;
    public int changeNum;
    public boolean changeNumUpGraded = false;
    DeadCellsCard father;



    public DeadCellsCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public DeadCellsCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }


    @Override
    public abstract void upgrade();

    @Override
    public abstract void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);

    @Override
    public AbstractCard makeCopy() {
        AbstractCard card = new Strike_king();
        try {
             card = this.getClass().newInstance();
            if (this.upgraded){
                card.upgrade();
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void upgradeBurnNumber(int number){
        this.burnNumber = this.baseBurnNumber += number;
        this.burnUpGraded = true;
    }

    public void upgradeMaxAmmunition(int num){
        this.maxAmmunitionNumber += num;
        this.ammunitionUpgraded = true;
    }

    public void upgradeHeavyDamage(int num){
        this.baseHeavyDamage += num;
        this.heavyDamage = this.baseHeavyDamage;
        this.heavyDamageUpgraded = true;
    }

    public void upgradeChangeNum(int num){
        this.baseChangeNum += num;
        this.changeNum = this.baseChangeNum;
        this.changeNumUpGraded = true;
    }

    //装填弹药
    public void fillInAmmunition(int ammunition){
        this.ammunitionNumber += ammunition;
        System.out.println(this.ammunitionNumber);
        if (ammunitionNumber>maxAmmunitionNumber){
            ammunitionNumber = maxAmmunitionNumber;
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        DeadCellsCard card = (DeadCellsCard) super.makeStatEquivalentCopy();
        card.magicNumber = this.magicNumber;
        card.ammunitionNumber = this.ammunitionNumber;
        card.maxAmmunitionNumber = this.maxAmmunitionNumber;
        card.ammunitionUpgraded = this.ammunitionUpgraded;
        card.burnNumber = this.burnNumber;
        card.baseBurnNumber = this.baseBurnNumber;
        card.burnUpGraded = this.burnUpGraded;
        card.baseHeavyDamage = this.baseHeavyDamage;
        card.heavyDamage = this.heavyDamage;
        card.heavyDamageUpgraded = this.heavyDamageUpgraded;
        card.baseChangeNum = this.baseChangeNum;
        card.changeNum = this.changeNum;
        card.changeNumUpGraded = this.changeNumUpGraded;
        card.father = this.father;
        return card;
    }

    void superApplyPowers(){
        super.applyPowers();
    }

    void superCalculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
    }

    public void heavyDamageApplyPower(){
        int realBase = this.baseDamage;
        this.baseDamage = this.baseHeavyDamage;
        super.applyPowers();
        this.heavyDamage = this.damage;
        this.baseDamage = realBase;
        super.applyPowers();
    }

    public void  calculateCardHeavyDamage(AbstractMonster mo){
        int realBaseDamage = this.baseDamage;
        this.baseDamage = this.baseHeavyDamage;
        super.calculateCardDamage(mo);
        this.heavyDamage = this.damage;
        this.baseDamage = realBaseDamage;
        super.calculateCardDamage(mo);
        this.isDamageModified = true;
    }
}
