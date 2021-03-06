/**
 * Copyright (c) 2016-~, Bosco.Liao (bosco_liao@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.huagang.QRcode.codegen.renderer;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.huagang.QRcode.codegen.qrcode.QreyesFormat;

/**
 * Deprecated reason: low recognition rate
 *
 * @author Bosco.Liao
 * @since 1.3.0
 */
@Deprecated
public class CBCPQreyesRenderer extends CBRPQreyesRenderer {

	@Override
	public void checkFormat(QreyesFormat format) {
		if (QreyesFormat.C_BORDER_C_POINT != format) {
			throw new IllegalArgumentException("Can only render C_BORDER_C_POINT, but got " + format);
		}
	}

	@Override
	public Shape getPointShape(double x, double y, double w, double h, double arcw, double arch) {
		return new Ellipse2D.Double(x, y, w, h);
	}

}
