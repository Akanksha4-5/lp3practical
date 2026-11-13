# StudentData - Code and Steps

Follow your lab manual if its code differs from this one.

## CODE (copy this into StudentData.sol)

```solidity
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract StudentData {

    struct Student {
        uint rollNo;
        string name;
        uint age;
        string course;
    }

    Student[] private students;
    uint public receivedEther;

    function addStudent(
        uint _rollNo,
        string memory _name,
        uint _age,
        string memory _course
    ) public {
        students.push(Student(_rollNo, _name, _age, _course));
    }

    function getStudent(uint index) public view
        returns (uint, string memory, uint, string memory)
    {
        require(index < students.length, "Invalid index");
        Student memory s = students[index];
        return (s.rollNo, s.name, s.age, s.course);
    }

    function getStudentCount() public view returns (uint) {
        return students.length;
    }

    // Runs when no function matches the call data
    fallback() external payable {
        receivedEther += msg.value;
    }
}
```

A yellow warning about "no receive function" is fine. The contract still works.

---

## STEPS

1. Open **remix.ethereum.org**.
2. File Explorer > `contracts` > New File > name it `StudentData.sol`.
3. Paste the code above. Save with Ctrl + S.
4. Open **Solidity Compiler**. Select `0.8.20`. Click **Compile**. Wait for the green tick.
5. Open **Deploy & Run Transactions**.
   - Environment: Remix VM
   - Contract: StudentData
   - Value: `0` Wei
   - Click **Deploy**.
6. Expand the deployed contract under **Deployed Contracts**.
7. In `addStudent`, enter this and click **Transact**:
   ```
   1, "Akanksha Matkar", 21, "BE"
   ```
   Text must be in double quotes.
8. In `getStudent`, enter `0` and click it. You see the record.
9. Click `getStudentCount`. Expected output: `1`.

## FALLBACK TEST (only if your practical asks for it)

1. Set **Value = 1 Wei** (top of Deploy & Run).
2. Scroll to the bottom of the deployed contract: **Low level interactions**.
3. In CALLDATA type `0x12345678`. Click **Transact**.
4. Click `receivedEther`. Expected output: `1`.
5. Set Value back to `0 Wei`.

If `receivedEther` shows 0, the Value was 0 Wei. Fix it and send again. Do not redeploy.

## MEMORY LINE

CREATE - COMPILE - DEPLOY - ADD - GET - COUNT - FALLBACK
