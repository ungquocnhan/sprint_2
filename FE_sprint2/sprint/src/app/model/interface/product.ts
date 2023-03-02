import {ImageProducts} from './image-products';
import {Manufacture} from './manufacture';

interface Port {
  id?: number;
  name?: string;
}

interface ButtonSupport {
  id?: number;
  name?: string;
}


interface FrequencyBand {
  id?: number;
  name?: string;
}

interface TypeDevice {
  id?: number;
  name?: string;
}

interface Anteing {
  id?: number;
  amount?: number;
}

interface TypeAnteing {
  id?: number;
  name?: string;
}

interface SpeedWifi {
  id?: number;
  name?: string;
}

interface StandardNetwork {
  id?: number;
  name?: string;
}

interface UserConnect {
  id?: number;
  name?: string;
}

interface CoverageDensity {
  id?: number;
  name?: string;
}

interface MadeIn {
  id?: number;
  name?: string;
}

interface Guarantee {
  id?: number;
  name?: string;
}

interface Promotion {
  id?: number;
  percentPromotion?: number;
}


export interface Product {
  id?: number;
  name?: string;
  price?: number;
  description?: string;
  size?: string;
  port?: Port;
  buttonSupport?: ButtonSupport;
  frequencyBand?: FrequencyBand;
  typeDevice?: TypeDevice;
  anteing?: Anteing;
  typeAnteing?: TypeAnteing;
  speedWifi?: SpeedWifi;
  standardNetwork?: StandardNetwork;
  userConnect?: UserConnect;
  coverageDensity?: CoverageDensity;
  manufacture?: Manufacture;
  madeIn?: MadeIn;
  guarantee?: Guarantee;
  promotion?: Promotion;
  imageProducts?: ImageProducts[];
}
