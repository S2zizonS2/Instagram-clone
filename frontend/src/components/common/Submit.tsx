import { css } from "@emotion/react";
import React from "react";

/**
 * Submit Button
 * @props props.value - 서브밋 버튼 이름
 * @returns 
 */
function Submit(props: { value: string }) {
    return (
        <button
            css={style}
            type={"submit"}
        >
            {props.value}
        </button>
    );
}

const style = css`
    border-radius: 8px;
    width: 100%;
    min-height: 2rem;
    cursor: pointer;
`;

export default Submit;