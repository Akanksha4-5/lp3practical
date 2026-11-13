# BankAccount - Code and Steps

Follow your lab manual if its code differs from this one.

## CODE (copy this into BankAccount.sol)

```solidity
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract BankAccount {

    address private owner;

    constructor() {
        owner = msg.sender;
    }

    function getOwner() public view returns (address) {
        return owner;
    }

    // Deposit exactly 2 Ether
    function deposit() external payable {
        require(msg.value == 2 ether, "Please send two ethers");
    }

    function withdraw() external {
        require(msg.sender == owner, "Only owner can withdraw");

        uint amount = address(this).balance;
        require(amount > 0, "Insufficient balance");

        (bool success, ) = payable(owner).call{value: amount}("");
        require(success, "Transfer failed");
    }

    function getBalance() public view returns (uint256) {
        return address(this).balance;
    }
}
```

---

## STEPS

1. Open **remix.ethereum.org**.
2. File Explorer > `contracts` > New File > name it `BankAccount.sol`.
3. Paste the code above. Save with Ctrl + S.
4. Open **Solidity Compiler**. Select `0.8.20`. Click **Compile**. Wait for the green tick.
5. Open **Deploy & Run Transactions**.
   - Environment: Remix VM (use the latest version listed)
   - Account: pick one and remember it
   - Value: `0` Wei
   - Contract: BankAccount
   - Click **Deploy**.
6. Expand `BankAccount` under **Deployed Contracts**.
7. Click `getOwner`. It shows the deployer address.
8. Deposit:
   - Value: `2`, unit: **Ether** (not Wei)
   - Click `deposit`.
9. Check balance:
   - Set Value back to `0 Wei`
   - Click `getBalance`. Expected: `2000000000000000000` (Wei)
10. Withdraw:
    - Keep Value at `0 Wei`
    - Use the **same account** you deployed with
    - Click `withdraw`
11. Click `getBalance` again. Expected: `0`.

## COMMON ERRORS

| Message | Fix |
|---|---|
| Please send two ethers | Value must be exactly 2 Ether |
| Only owner can withdraw | Switch back to the deploying account |
| Insufficient balance | Deposit first |
| Deploy fails | Value must be 0 Wei at deployment |

## MEMORY LINE

CREATE - CODE - COMPILE - DEPLOY (0 Wei) - OWNER - DEPOSIT (2 Ether) - BALANCE - WITHDRAW - BALANCE (0)
