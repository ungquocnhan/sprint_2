import {ProductInfo} from './product-info';

export interface OderDetailInfo {
  quantityBuy?: number;

  createDate?: string;

  price?: number;

  statusPay?: boolean;

  product?: ProductInfo;
}
