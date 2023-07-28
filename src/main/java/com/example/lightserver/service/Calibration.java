package com.example.lightserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Calibration {
    public double getTriY(int id, int r, int g, int b) {
        switch (id) {
            case 1:
                return -0.279092984 + r * 0.061654097 + g * 0.161100176 + b * (-0.029059113);
            case 2:
                return -0.298626322 + r * 0.05836012 + g * 0.163846657 + b * (-0.027835675);
            case 3:
                return -0.221772775 + r * 0.056878566 + g * 0.158382467 + b * (-0.023548041);
            case 4:
                return -0.16430792 + r * 0.063095764 + g * 0.160295708 + b * (-0.021687505);
            case 5:
                return -0.38918913 + r * 0.058935596 + g * 0.162401518 + b * (-0.027476641);
            case 6:
                return -0.246702845 + r * 0.055949055 + g * 0.16757628 + b * (-0.033882516);
            case 7:
                return -0.524427825 + r * 0.062731508 + g * 0.154131538 + b * (-0.020061495);
            case 8:
                return -0.508242904 + r * 0.061345528 + g * 0.152840325 + b * (-0.016371799);
            case 9:
                return -0.104275138 + r * 0.057407496 + g * 0.162521395 + b * (-0.025568323);

        }
        return 0;
    }

    public  double getTriX(int id, int r, int g, int b) {
        switch (id) {
            case 1:
                return -0.540033472 + r * 0.130464893 + g * 0.078893784 + b * 0.029335389;
            case 2:
                return -0.575092428 + r * 0.123271456 + g * 0.079547052 + b * 0.032455042;
            case 3:
                return -0.324803165 + r * 0.118093792 + g * 0.07823233 + b * 0.032888966;
            case 4:
                return -0.28096381 + r * 0.130414637 + g * 0.075902137 + b * 0.039594168;
            case 5:
                return -0.633737209 + r * 0.123067303 + g * 0.081651477 + b * 0.028675858;
            case 6:
                return -0.57456887 + r * 0.118958246 + g * 0.086940139 + b * 0.022386588;
            case 7:
                return -0.869105866 + r * 0.130183542 + g * 0.073675039 + b * 0.036632388;
            case 8:
                return -0.785511404 + r * 0.124448107 + g * 0.071446505 + b * 0.042158064;
            case 9:
                return -0.415970471 + r * 0.121443155 + g * 0.079931136 + b * 0.031209955;

        }
        return 0;
    }

    public  double getTriZ(int id, int r, int g, int b) {
        switch (id) {
            case 1:
                return -0.169093724 + r * (-0.015753723) + g * 0.022402746 + b * 0.325903455;
            case 2:
                return -0.287808745 + r * (-0.012143219) + g * 0.01699743 + b * 0.34384724;
            case 3:
                return -0.02029488 + r * (-0.013353969) + g * 0.020696496 + b * 0.33751459;
            case 4:
                return -0.056875789 + r * (-0.013156724) + g * 0.017156471 + b * 0.357163819;
            case 5:
                return 0.036094746 + r * (-0.014357302) + g * 0.021125084 + b * 0.336536356;
            case 6:
                return -0.029516061 + r * (-0.013936281) + g * 0.021163117 + b * 0.339874629;
            case 7:
                return -0.099110836 + r * (-0.011766579) + g * 0.013851022 + b * 0.340957969;
            case 8:
                return -0.331990358 + r * (-0.01069218) + g * 0.014445812 + b * 0.347027302;
            case 9:
                return -0.052460977 + r * (-0.0159319) + g * 0.024790711 + b * 0.328395022;

        }
        return 0;
    }

    public double getCct(int id, int r, int g, int b) {
        double x = getTriX(id, r, g, b);
        double y = getTriY(id, r, g, b);
        double z = getTriZ(id, r, g, b);
        double X = x / (x + y + z);
        double Y = y / (x + y + z);
        double n = (X - 0.332) / (Y - 0.1858);
        double cct = -437 * n * n * n + 3607 * n * n - 6861 * n + 5514.31;
        return cct;
    }

}
